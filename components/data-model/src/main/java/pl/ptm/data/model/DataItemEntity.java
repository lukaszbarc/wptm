package pl.ptm.data.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Entity
public class DataItemEntity {


    @Id
    @GeneratedValue
    private long identity;

    private double lat;

    private double lon;

    private String line;

    private String firstLine;

    private int brigade;

    private String status;

    private Date date;

    @ManyToOne
    private DataSnapshotEntity dataSnapshot;

}
