package pl.ptm.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ptm.client.api.VehiclePositionData;
import pl.ptm.client.service.api.VehicleCurrentPositionService;
import pl.ptm.client.service.impl.exception.VehicleNotFoundException;
import pl.ptm.data.dao.jpa.DataDaoJpa;
import pl.ptm.data.dao.jpa.DataItemDaoJpa;
import pl.ptm.data.model.DataItemEntity;

import java.util.ArrayList;
import java.util.List;


@Service
public class VehicleCurrentPositionServiceImpl implements VehicleCurrentPositionService {

    @Autowired
    private DataDaoJpa dataDao;

    @Autowired
    private DataItemDaoJpa dataItemDao;

    @Override
    public VehiclePositionData getVehiclePositionData(String providerId, String lineName, int brigadeNumber) {
        DataItemEntity dataItem = dataItemDao
                .findFirst1ByLineAndBrigadeOrderByDataSnapshotIdentityDesc(
                        Integer.parseInt(lineName),
                        brigadeNumber);

        if (dataItem != null) {
            VehiclePositionData vehiclePositionData = new VehiclePositionData();
            vehiclePositionData.setLat(dataItem.getLat());
            vehiclePositionData.setLon(dataItem.getLon());
            vehiclePositionData.setStatus(dataItem.getStatus());
            vehiclePositionData.setShortName(lineName);
            vehiclePositionData.setFullName(lineName + " brigade " + brigadeNumber);
            vehiclePositionData.setDescription("");
            return vehiclePositionData;
        } else {
            throw new VehicleNotFoundException();
        }
    }

    @Override
    public List<VehiclePositionData> getVehiclePositionData(String providerId, String lineName) {
        return new ArrayList<>();
    }

    @Override
    public List<VehiclePositionData> getVehiclePositionData(String providerId) {
        return new ArrayList<>();
    }
}
