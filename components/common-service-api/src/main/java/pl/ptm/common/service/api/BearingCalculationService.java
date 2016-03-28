package pl.ptm.common.service.api;

public interface BearingCalculationService {

    double calculateBearing(double lastLon, double lastLat, double currentLon, double currentLat);
}
