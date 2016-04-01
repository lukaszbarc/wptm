package pl.ptm.data.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.ptm.data.enums.VehicleStopType;
import pl.ptm.data.model.VehicleStopEntity;

import java.util.*;

/**
 * Created by jbogacz on 25.03.2016.
 */
public class ZtmParsingStrategy implements ParsingStrategy<VehicleStopEntity>{
    private static final Logger LOGGER = LoggerFactory.getLogger(ZtmParsingStrategy.class);

    private List<VehicleStopEntity> stops = new LinkedList<>();
    private VehicleStopEntity stop;
    private String groupName;
    private String groupNumber;

    @Override
    public void doParse(String line) {
        if(ZtmFormatUtils.isStopsGroupSection(line)){
            groupNumber = ZtmFormatUtils.parseStringAt(3, line);
            groupName = ZtmFormatUtils.parseStringAt(10, line);
        }

        if(ZtmFormatUtils.isStop(line)){
            stop = new VehicleStopEntity();
            stop.setGroupNumber(groupNumber);
            stop.setGroupName(groupName);
            stop.setNumber(ZtmFormatUtils.parseStringAt(9,line));
            stop.setName(ZtmFormatUtils.parseStringAt(34, line));
            stop.setDest(ZtmFormatUtils.parseStringAt(75, line));
            stop.setLon(ZtmFormatUtils.parseDoubleAt(112, line));
            stop.setLat(ZtmFormatUtils.parseDoubleAt(129, line));
            stop.setType(VehicleStopType.TRAM);

            addStopAndLogDetails();
        }

        if(ZtmFormatUtils.isVehiclesList(line)){
            Set<Long> trams = ZtmFormatUtils.parseTramsNumbers(40, line);
            stop.setTrams(trams);
        }
    }

    @Override
    public void doCleaning() {
        for(Iterator<VehicleStopEntity> iter = stops.iterator(); iter.hasNext();) {
            VehicleStopEntity vehicleStopEntity = iter.next();
            if(isNoTrams(vehicleStopEntity)){
                LOGGER.info("Removing stop: " + vehicleStopEntity);
                iter.remove();
            }
        }
        LOGGER.info("After cleaning stops left: " + stops.size());
    }

    @Override
    public List<VehicleStopEntity> getData() {
        return stops;
    }

    private boolean isNoTrams(VehicleStopEntity vehicleStopEntity) {
        return vehicleStopEntity.getTrams() == null || vehicleStopEntity.getTrams().size() == 0;
    }

    private void addStopAndLogDetails() {
        LOGGER.debug("Adding stop: " + stop);
        stops.add(stop);
    }
}
