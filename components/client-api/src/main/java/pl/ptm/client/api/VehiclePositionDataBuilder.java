package pl.ptm.client.api;

public class VehiclePositionDataBuilder {
    private double lat;
    private double lon;
    private double nextLat;
    private double nextLon;
    private double calculatedSpeed;
    private double bearing;
    private String providerId;
    private String shortName;
    private String fullName;
    private String description;
    private String status;

    private VehiclePositionDataBuilder() {
    }

    public static VehiclePositionDataBuilder aVehiclePositionData() {
        return new VehiclePositionDataBuilder();
    }

    public VehiclePositionDataBuilder withLat(double lat) {
        this.lat = lat;
        return this;
    }

    public VehiclePositionDataBuilder withLon(double lon) {
        this.lon = lon;
        return this;
    }

    public VehiclePositionDataBuilder withNextLat(double nextLat) {
        this.nextLat = nextLat;
        return this;
    }

    public VehiclePositionDataBuilder withNextLon(double nextLon) {
        this.nextLon = nextLon;
        return this;
    }

    public VehiclePositionDataBuilder withCalculatedSpeed(double calculatedSpeed) {
        this.calculatedSpeed = calculatedSpeed;
        return this;
    }

    public VehiclePositionDataBuilder withBearing(double bearing) {
        this.bearing = bearing;
        return this;
    }

    public VehiclePositionDataBuilder withProviderId(String providerId) {
        this.providerId = providerId;
        return this;
    }

    public VehiclePositionDataBuilder withShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public VehiclePositionDataBuilder withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public VehiclePositionDataBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public VehiclePositionDataBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public VehiclePositionDataBuilder but() {
        return aVehiclePositionData().withLat(lat).withLon(lon).withNextLat(nextLat).withNextLon(nextLon).withCalculatedSpeed(calculatedSpeed).withBearing(bearing).withProviderId(providerId).withShortName(shortName).withFullName(fullName).withDescription(description).withStatus(status);
    }

    public VehiclePositionData build() {
        VehiclePositionData vehiclePositionData = new VehiclePositionData();
        vehiclePositionData.setLat(lat);
        vehiclePositionData.setLon(lon);
        vehiclePositionData.setNextLat(nextLat);
        vehiclePositionData.setNextLon(nextLon);
        vehiclePositionData.setCalculatedSpeed(calculatedSpeed);
        vehiclePositionData.setBearing(bearing);
        vehiclePositionData.setProviderId(providerId);
        vehiclePositionData.setShortName(shortName);
        vehiclePositionData.setFullName(fullName);
        vehiclePositionData.setDescription(description);
        vehiclePositionData.setStatus(status);
        return vehiclePositionData;
    }
}
