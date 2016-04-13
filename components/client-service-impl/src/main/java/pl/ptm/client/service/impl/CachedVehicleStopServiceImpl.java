package pl.ptm.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.ptm.client.api.VehicleStopData;
import pl.ptm.client.service.api.CachedVehicleStopService;
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

    @Override
    @Cacheable("vehiclesStops")
    public List<VehicleStopData> getRegisteredVehicleStops() {
        List<VehicleStopData> vehicleStopList = new ArrayList<>();
        for (VehicleStopEntity entity : dao.findAll()) {
            vehicleStopList.add(converter.toRight(entity));
        }
        return vehicleStopList;
    }

}
