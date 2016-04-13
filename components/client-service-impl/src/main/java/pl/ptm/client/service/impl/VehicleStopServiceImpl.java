package pl.ptm.client.service.impl;

import com.grum.geocalc.DegreeCoordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.ptm.client.api.VehicleStopData;
import pl.ptm.client.service.api.CachedVehicleStopService;
import pl.ptm.client.service.api.VehicleStopService;

import java.text.DecimalFormat;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * Created by jbogacz on 2016-04-01.
 */
@Component
public class VehicleStopServiceImpl implements VehicleStopService {

    private static final double ROUND_DIGITS = 1000.0;

    @Autowired
    private CachedVehicleStopService cachedVehicleStopService;

    @Override
    public List<VehicleStopData> getRegisteredVehicleStops() {
        return cachedVehicleStopService.getRegisteredVehicleStops();
    }

    @Override
    public VehicleStopData getNearestVehicleStop(Double lon, Double lat) {
        return cachedVehicleStopService.getNearestVehicleStop(
                round(lon),
                round(lat));
    }

    private Double round(Double value){
        return Math.round( value * ROUND_DIGITS ) / ROUND_DIGITS;
    }
}
