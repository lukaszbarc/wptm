package pl.ptm.data.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(indexes = {@Index(name = "provider_line_brigade_idx", columnList = "providerId,lineName,brigade")},
        uniqueConstraints = {@UniqueConstraint(name = "vehicle_current_position_entity_unique", columnNames = {"providerId", "lineName", "brigade"})})
public class VehicleCurrentPositionEntity {

    @Id
    @GeneratedValue
    private long identity;

    @Column(nullable = false)
    private String providerId;
    @Column(nullable = false)
    private String lineName;
    @Column(nullable = false)
    private int brigade;

    @Column(nullable = false)
    private double currentLon;
    @Column(nullable = false)
    private double currentLat;

    private double lastLon;
    private double lastLat;

    private double bearing;
    private double calculatedSpeed;

    private Date lastPositionDate;
    private Date currentPositionDate;
}
