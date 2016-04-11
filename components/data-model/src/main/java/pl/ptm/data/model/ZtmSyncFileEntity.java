package pl.ptm.data.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by JBOGACZ on 2016-04-06.
 */
@Entity
@Data
public class ZtmSyncFileEntity {

    @Id
    private Long id;

    private String name;

    private Date ftpDate;

}
