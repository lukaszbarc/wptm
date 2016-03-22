package pl.ptm.client.service.api;


import pl.ptm.client.api.VehiclePositionData;

import java.util.List;

public interface VehicleCurrentPositionService {

    VehiclePositionData getVehiclePositionData(String providerId, String lineName, int brigadeNumber);

    List<VehiclePositionData> getVehiclePositionData(String providerId, String lineName);

    List<VehiclePositionData> getVehiclePositionData(String providerId);
}
