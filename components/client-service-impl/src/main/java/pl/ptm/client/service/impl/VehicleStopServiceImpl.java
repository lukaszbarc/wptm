package pl.ptm.client.service.impl;

import com.grum.geocalc.Coordinate;
import com.grum.geocalc.DegreeCoordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import pl.ptm.client.api.VehicleStopData;
import pl.ptm.client.service.api.VehicleStopService;
import pl.ptm.data.dao.jpa.VehicleStopDaoJpa;
import pl.ptm.data.model.VehicleStopEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jbogacz on 2016-04-01.
 */
@Component
public class VehicleStopServiceImpl implements VehicleStopService {

    @Autowired
    private VehicleStopDaoJpa dao;

    @Autowired
    private VehicleStopConverter converter;

    @Override
    @Cacheable("vehiclesStops")
    public List<VehicleStopData> getRegisteredVehicleStops() {
        List<VehicleStopData> vehicleStopList = new ArrayList<>();
        for (VehicleStopEntity entity : dao.findAll()) {
            vehicleStopList.add(converter.toRight(entity));
        }
        return vehicleStopList;
    }

    @Override
    public VehicleStopData getNearestVehicleStop(Double lon, Double lat) {
        Point stand = new Point(new DegreeCoordinate(lon), new DegreeCoordinate(lon));

        Double smallestDist = null;
        VehicleStopData nearestVehicleStop = null;

        for (VehicleStopData stopData : getRegisteredVehicleStops()) {
            if(stopData.getLon() == null || stopData.getLat() == null){
                continue;
            }

            Point fore = new Point(new DegreeCoordinate(stopData.getLon()), new DegreeCoordinate(stopData.getLat()));

            Double distance = EarthCalc.getDistance(stand, fore);
            if(smallestDist == null || distance < smallestDist){
                smallestDist = distance;
                nearestVehicleStop = stopData;
            }
        }
        return nearestVehicleStop;
    }
}
