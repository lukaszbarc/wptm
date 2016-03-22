package pl.ptm.data.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class DataSnapshotEntity {

    @Id
    @GeneratedValue
    private long identity;

    @OneToMany(mappedBy = "dataSnapshot", cascade = CascadeType.PERSIST)
    private List<DataItemEntity> items;

    private String providerId;

    private Date date;
}
