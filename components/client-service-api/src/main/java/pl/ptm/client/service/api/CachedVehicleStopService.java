package pl.ptm.client.service.api;

import pl.ptm.client.api.VehicleStopData;

import java.util.List;

/**
 * Created by JBOGACZ on 2016-04-11.
 */
public interface CachedVehicleStopService {

    List<VehicleStopData> getRegisteredVehicleStops();

    public VehicleStopData getNearestVehicleStop(Double lon, Double lat);
}
