package pl.ptm.client.service.api;


import pl.ptm.client.api.GeoPointData;
import pl.ptm.client.api.VehicleDistanceData;

import java.util.List;

public interface VehicleCurrentDistanceService {

    List<VehicleDistanceData> getVehicleDistanceData(final GeoPointData geoPoint,
                                                     final String providerId,
                                                     final String lineName,
                                                     final double maxDistance);

    List<VehicleDistanceData> getVehicleDistanceData(final GeoPointData build,
                                                     final String providerId,
                                                     final double maxDistance);
}
