package pl.ptm.client.service.api;

import pl.ptm.client.api.VehicleStopData;

import java.util.List;

/**
 * Created by jbogacz on 2016-04-01.
 */
public interface VehicleStopService {

    List<VehicleStopData> getRegisteredVehicleStops();

    VehicleStopData getNearestVehicleStop(Double lon, Double lat);
}
