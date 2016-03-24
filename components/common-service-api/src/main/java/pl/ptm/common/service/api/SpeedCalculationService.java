package pl.ptm.common.service.api;

public interface SpeedCalculationService {

    double calculateSpeedInKph(double distanceInMeters, double deltaTimeInSeconds);
}
