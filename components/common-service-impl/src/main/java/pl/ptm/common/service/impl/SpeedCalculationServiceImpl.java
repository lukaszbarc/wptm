package pl.ptm.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.ptm.common.service.api.SpeedCalculationService;


public class SpeedCalculationServiceImpl implements SpeedCalculationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistanceCalculationServiceImpl.class);

    @Override
    public double calculateSpeedInKph(double distanceInMeters, double deltaTimeInSeconds) {
        if (deltaTimeInSeconds == 0.0) {
            return 0.0;
        }
        LOGGER.trace("Calculate speed for {} meters in {} seconds", distanceInMeters, deltaTimeInSeconds);

        double result = (distanceInMeters * (3600.0 / deltaTimeInSeconds)) / 1000.0;

        LOGGER.trace("Calculated speed : {}", result);
        return result;
    }
}
