package pl.ptm.client.api;

public class GeoPointDataBuilder {
    private double longitude;
    private double latitude;
    private double elevation;

    private GeoPointDataBuilder() {
    }

    public static GeoPointDataBuilder aGeoPointData() {
        return new GeoPointDataBuilder();
    }

    public GeoPointDataBuilder withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public GeoPointDataBuilder withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public GeoPointDataBuilder withElevation(double elevation) {
        this.elevation = elevation;
        return this;
    }

    public GeoPointDataBuilder but() {
        return aGeoPointData()
                .withLongitude(longitude)
                .withLatitude(latitude)
                .withElevation(elevation);
    }

    public GeoPointData build() {
        GeoPointData geoPointData = new GeoPointData();
        geoPointData.setLongitude(longitude);
        geoPointData.setLatitude(latitude);
        geoPointData.setElevation(elevation);
        return geoPointData;
    }
}
