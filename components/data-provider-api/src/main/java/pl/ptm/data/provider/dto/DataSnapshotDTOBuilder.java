package pl.ptm.data.provider.dto;

import java.util.Date;
import java.util.List;

public class DataSnapshotDTOBuilder {
    private List<DataItemDTO> items;
    private String providerId;
    private Date date;

    private DataSnapshotDTOBuilder() {
    }

    public static DataSnapshotDTOBuilder aDataSnapshotDTO() {
        return new DataSnapshotDTOBuilder();
    }

    public DataSnapshotDTOBuilder withItems(List<DataItemDTO> items) {
        this.items = items;
        return this;
    }

    public DataSnapshotDTOBuilder withProviderId(String providerId) {
        this.providerId = providerId;
        return this;
    }

    public DataSnapshotDTOBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    public DataSnapshotDTOBuilder but() {
        return aDataSnapshotDTO()
                .withItems(items)
                .withProviderId(providerId)
                .withDate(date);
    }

    public DataSnapshotDTO build() {
        DataSnapshotDTO dataSnapshotDTO = new DataSnapshotDTO();
        dataSnapshotDTO.setItems(items);
        dataSnapshotDTO.setProviderId(providerId);
        dataSnapshotDTO.setDate(date);
        return dataSnapshotDTO;
    }
}
