package pl.ptm.data.service;

public interface BearingCalculationService {

    double calculateBearing(double lastLon, double lastLat, double currentLon, double currentLat);
}
