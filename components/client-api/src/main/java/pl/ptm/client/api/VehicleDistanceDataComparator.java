package pl.ptm.client.api;

import java.util.Comparator;

public class VehicleDistanceDataComparator implements Comparator<VehicleDistanceData> {

    @Override
    public int compare(VehicleDistanceData current, VehicleDistanceData other) {
        if (current.getDistance() < other.getDistance()) {
            return -1;
        }
        if (current.getDistance() > other.getDistance()) {
            return 1;
        }
        return 0;
    }
}
