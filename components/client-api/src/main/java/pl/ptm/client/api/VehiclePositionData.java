package pl.ptm.client.api;


import lombok.Data;

@Data
public class VehiclePositionData {

    private double lat;
    private double lon;
    private double calculatedSpeed;
    private double bearing;
    private String providerId;
    private String shortName;
    private String fullName;
    private String description;
    private String status;
}
