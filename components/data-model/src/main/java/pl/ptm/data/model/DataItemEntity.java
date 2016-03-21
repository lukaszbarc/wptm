package pl.ptm.data.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
public class DataItemEntity {


    @Id
    @GeneratedValue
    private long identity;

    private double lat;

    private double lon;

    private int line;

    private int firstLine;

    private int brigade;

    private String status;

    private Date date;

    @ManyToOne
    private DataSnapshotEntity dataSnapshotEntity;

}
