package pl.ptm.client.api;

import lombok.Data;

@Data
public class GeoPointData {

    private double longitude;
    private double latitude;
    private double elevation;
}
