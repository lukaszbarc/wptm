package pl.ptm.data.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.ptm.data.model.VehicleStopEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jbogacz on 25.03.2016.
 */
public class ZtmParsingStrategy implements ParsingStrategy<VehicleStopEntity>{
    private static final Logger LOGGER = LoggerFactory.getLogger(ZtmParsingStrategy.class);

    private List<VehicleStopEntity> stops = new ArrayList<>();

    private String groupName;
    private String groupNumber;

    @Override
    public void doParse(String line) {

        if(ZtmFormatUtils.isStopsGroupSection(line)){
            groupNumber = ZtmFormatUtils.parseStringAt(3, line);
            groupName = ZtmFormatUtils.parseStringAt(10, line);
        }

        if(ZtmFormatUtils.isStop(line)){

            VehicleStopEntity stop = new VehicleStopEntity();
            stop.setGroupNumber(groupNumber);
            stop.setGroupName(groupName);
            stop.setNumber(ZtmFormatUtils.parseStringAt(9,line));
            stop.setName(ZtmFormatUtils.parseStringAt(34, line));
            stop.setDest(ZtmFormatUtils.parseStringAt(75, line));
            stops.add(stop);

            LOGGER.debug("Stop added: " + stop);
        }
    }

    @Override
    public List<VehicleStopEntity> getData() {
        return stops;
    }
}
