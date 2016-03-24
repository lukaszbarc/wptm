package pl.ptm.client.api;

import lombok.Data;

@Data
public class VehicleDistanceData {

    private VehiclePositionData vehiclePositionData;
    private GeoPointData point;
    private double distance;

}
