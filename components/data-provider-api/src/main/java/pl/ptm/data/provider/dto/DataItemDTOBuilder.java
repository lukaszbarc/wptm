package pl.ptm.data.provider.dto;

import java.util.Date;


public class DataItemDTOBuilder {
    private double lat;
    private double lon;
    private String line;
    private String firstLine;
    private int brigade;
    private String status;
    private Date date;

    private DataItemDTOBuilder() {
    }

    public static DataItemDTOBuilder aDataItemDTO() {
        return new DataItemDTOBuilder();
    }


    public DataItemDTOBuilder withLat(double lat) {
        this.lat = lat;
        return this;
    }

    public DataItemDTOBuilder withLon(double lon) {
        this.lon = lon;
        return this;
    }

    public DataItemDTOBuilder withLine(String line) {
        this.line = line;
        return this;
    }

    public DataItemDTOBuilder withFirstLine(String firstLine) {
        this.firstLine = firstLine;
        return this;
    }

    public DataItemDTOBuilder withBrigade(int brigade) {
        this.brigade = brigade;
        return this;
    }

    public DataItemDTOBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public DataItemDTOBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    public DataItemDTOBuilder but() {
        return aDataItemDTO()
                .withLat(lat)
                .withLon(lon)
                .withLine(line)
                .withFirstLine(firstLine)
                .withBrigade(brigade)
                .withStatus(status)
                .withDate(date);
    }

    public DataItemDTO build() {
        DataItemDTO dataItemDTO = new DataItemDTO();
        dataItemDTO.setLat(lat);
        dataItemDTO.setLon(lon);
        dataItemDTO.setLine(line);
        dataItemDTO.setFirstLine(firstLine);
        dataItemDTO.setBrigade(brigade);
        dataItemDTO.setStatus(status);
        dataItemDTO.setDate(date);
        return dataItemDTO;
    }
}
