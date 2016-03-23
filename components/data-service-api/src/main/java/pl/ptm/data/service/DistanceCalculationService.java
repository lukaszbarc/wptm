package pl.ptm.data.service;


public interface DistanceCalculationService {

    double calculateDistanceInMeters(double lastLon, double lastLat, double currentLon, double currentLat);
}
