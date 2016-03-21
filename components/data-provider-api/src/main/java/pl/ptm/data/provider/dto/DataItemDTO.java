package pl.ptm.data.provider.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DataItemDTO {

    private long identity;
    private double lat;
    private double lon;
    private int line;
    private int brigade;
    private String status;
    private Date date;

}
