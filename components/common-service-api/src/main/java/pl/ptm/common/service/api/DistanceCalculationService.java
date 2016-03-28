package pl.ptm.common.service.api;


public interface DistanceCalculationService {

    double calculateDistanceInMeters(double lastLon, double lastLat, double currentLon, double currentLat);
}
