package pl.ptm.common.service.impl;

import com.grum.geocalc.Coordinate;
import com.grum.geocalc.DegreeCoordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.ptm.common.service.api.DistanceCalculationService;

public class DistanceCalculationServiceImpl implements DistanceCalculationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistanceCalculationServiceImpl.class);

    @Override
    public double calculateDistanceInMeters(double lastLon, double lastLat, double currentLon, double currentLat) {
        Coordinate lastPosLon = new DegreeCoordinate(lastLon);
        Coordinate lastPosLat = new DegreeCoordinate(lastLat);

        Coordinate currentPosLon = new DegreeCoordinate(currentLon);
        Coordinate currentPosLat = new DegreeCoordinate(currentLat);

        Point lastPositionPoint = new Point(lastPosLat, lastPosLon);
        Point currentPositionPoint = new Point(currentPosLat, currentPosLon);

        LOGGER.trace("Calculate distance between {} and {}", lastPositionPoint, currentPositionPoint);

        double result = EarthCalc.getDistance(lastPositionPoint, currentPositionPoint);

        LOGGER.trace("Calculated distance : {}", result);
        return result;
    }
}
