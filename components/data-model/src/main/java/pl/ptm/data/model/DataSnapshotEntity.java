package pl.ptm.data.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
public class DataSnapshotEntity {

    @Id
    @GeneratedValue
    private long identity;

    @OneToMany(mappedBy = "dataSnapshotEntity")
    private List<DataItemEntity> items;

    private String providerId;

    private Date date;
}
