package pl.ptm.data.provider.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DataSnapshotDTO {

    private long identity;
    private List<DataItemDTO> items;
    private String providerId;
    private Date date;
}
