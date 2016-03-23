package pl.ptm.data.service.impl;

import com.grum.geocalc.Coordinate;
import com.grum.geocalc.DegreeCoordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.ptm.data.service.BearingCalculationService;

public class BearingCalculationServiceImpl implements BearingCalculationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BearingCalculationServiceImpl.class);

    @Override
    public double calculateBearing(double lastLon, double lastLat, double currentLon, double currentLat) {
        Coordinate lastPosLon = new DegreeCoordinate(lastLon);
        Coordinate lastPosLat = new DegreeCoordinate(lastLat);

        Coordinate currentPosLon = new DegreeCoordinate(currentLon);
        Coordinate currentPosLat = new DegreeCoordinate(currentLat);

        Point lastPositionPoint = new Point(lastPosLat, lastPosLon);
        Point currentPositionPoint = new Point(currentPosLat, currentPosLon);

        LOGGER.trace("Calculate bearing between {} and {}", lastPositionPoint, currentPositionPoint);

        double result = EarthCalc.getBearing(lastPositionPoint, currentPositionPoint);

        LOGGER.trace("Calculated bearing : {}", result);

        return result;
    }
}
