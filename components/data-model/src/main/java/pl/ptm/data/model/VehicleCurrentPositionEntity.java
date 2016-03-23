package pl.ptm.data.model;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(indexes = {@Index(name = "provider_line_brigade_idx", columnList = "providerId,lineName,brigade")})
public class VehicleCurrentPositionEntity {

    @Id
    @GeneratedValue
    private long identity;

    private String providerId;
    private String lineName;
    private int brigade;

    private double currentLon;
    private double currentLat;

    private double lastLon;
    private double lastLat;

    private int heading;
    private double calculatedSpeed;

    @LastModifiedDate
    private Date currentPositionData;

    private Date lastPositionData;
}
