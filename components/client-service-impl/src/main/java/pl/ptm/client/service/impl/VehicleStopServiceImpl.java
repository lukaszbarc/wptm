package pl.ptm.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<VehicleStopData> getRegisteredVehicleStops() {
        List<VehicleStopData> vehicleStopList = new ArrayList<>();
        for (VehicleStopEntity entity : dao.findAll()) {
            vehicleStopList.add(converter.toRight(entity));
        }
        return vehicleStopList;
    }
}
