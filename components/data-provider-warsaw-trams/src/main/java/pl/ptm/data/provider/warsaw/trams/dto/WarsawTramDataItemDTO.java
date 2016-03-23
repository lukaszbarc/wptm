package pl.ptm.data.provider.warsaw.trams.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class WarsawTramDataItemDTO {

    @JsonProperty("Brigade")
    private String brigade;

    @JsonProperty("FirstLine")
    private String firstLine;

    @JsonProperty("Lines")
    private String lines;

    @JsonProperty("Lat")
    private double lat;

    @JsonProperty("Lon")
    private double lon;

    @JsonProperty("LowFloor")
    private boolean lowFloor;

    @JsonProperty("Status")
    private String status;

    @JsonProperty("Time")
    private Date time;
}
