package pl.ptm.client.api;

import lombok.Data;

import java.util.Set;

/**
 * Created by JBOGACZ on 2016-03-29.
 */
@Data
public class VehicleStopData {

    private Double lat;

    private Double lon;

    private String name;

    private String dest;

}
