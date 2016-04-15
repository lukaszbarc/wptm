package pl.ptm.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.ptm.client.api.VehicleStopData;
import pl.ptm.client.service.api.CachedVehicleStopService;
import pl.ptm.common.service.api.DistanceCalculationService;
import pl.ptm.data.dao.jpa.VehicleStopDaoJpa;
import pl.ptm.data.model.VehicleStopEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JBOGACZ on 2016-04-11.
 */
@Service
public class CachedVehicleStopServiceImpl implements CachedVehicleStopService {

    @Autowired
    private VehicleStopDaoJpa dao;

    @Autowired
    private VehicleStopConverter converter;

    @Autowired

    @Cacheable("vehiclesStops")
    @Override
    public List<VehicleStopData> getRegisteredVehicleStops() {
        List<VehicleStopData> vehicleStopList = new ArrayList<>();
        for (VehicleStopEntity entity : dao.findAll()) {
            vehicleStopList.add(converter.toRight(entity));
        }
        return vehicleStopList;
    }

    @Cacheable("nearestStop")
    @Override
    public VehicleStopData getNearestVehicleStop(Double lon, Double lat) {

        Double smallestDist = null;
        VehicleStopData nearestVehicleStop = null;

        for (VehicleStopData stopData : getRegisteredVehicleStops()) {
            if(stopData.getLon() == null || stopData.getLat() == null){
                continue;
            }
            double currDistance = calculateDistanceInKm(lon, lat, stopData.getLon(), stopData.getLat());
            if(smallestDist == null || currDistance < smallestDist){
                smallestDist = currDistance;
                nearestVehicleStop = stopData;
            }
        }
        return nearestVehicleStop;
    }

    private double calculateDistanceInKm(double lon1, double lat1, double lon2, double lat2) {
        return distance(lon1, lat1, lon2, lat2);
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

}
