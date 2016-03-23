package pl.ptm.data.provider.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DataItemDTO {

    private double lat;
    private double lon;
    private String line;
    private String firstLine;
    private int brigade;
    private String status;
    private Date date;

}
