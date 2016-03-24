package pl.ptm.client.api;

public class VehicleDistanceDataBuilder {

    private VehiclePositionData vehiclePositionData;
    private GeoPointData point;
    private double distance;

    private VehicleDistanceDataBuilder() {
    }

    public static VehicleDistanceDataBuilder aVehicleDistanceData() {
        return new VehicleDistanceDataBuilder();
    }

    public VehicleDistanceDataBuilder withVehiclePositionData(VehiclePositionData vehiclePositionData) {
        this.vehiclePositionData = vehiclePositionData;
        return this;
    }

    public VehicleDistanceDataBuilder withPoint(GeoPointData point) {
        this.point = point;
        return this;
    }

    public VehicleDistanceDataBuilder withDistance(double distance) {
        this.distance = distance;
        return this;
    }

    public VehicleDistanceDataBuilder but() {
        return aVehicleDistanceData()
                .withVehiclePositionData(vehiclePositionData)
                .withPoint(point)
                .withDistance(distance);
    }

    public VehicleDistanceData build() {
        VehicleDistanceData vehicleDistanceData = new VehicleDistanceData();
        vehicleDistanceData.setVehiclePositionData(vehiclePositionData);
        vehicleDistanceData.setPoint(point);
        vehicleDistanceData.setDistance(distance);
        return vehicleDistanceData;
    }
}
