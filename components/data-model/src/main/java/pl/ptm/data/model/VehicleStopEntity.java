package pl.ptm.data.model;

import lombok.Data;
import pl.ptm.data.enums.VehicleStopType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Set;

/**
 * Created by jbogacz on 24.03.2016.
 */
@Entity
@Data
public class VehicleStopEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Double lat;

    private Double lon;

    private String name;

    private String dest;

    private String number;

    private String groupNumber;

    private String groupName;

    private VehicleStopType type;

    @Transient
    private Set<Long> trams;

    @Override
    public String toString() {
        return "VehicleStopEntity{" +
                "id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                ", name='" + name + '\'' +
                ", dest='" + dest + '\'' +
                ", number='" + number + '\'' +
                ", groupNumber='" + groupNumber + '\'' +
                ", groupName='" + groupName + '\'' +
                ", type=" + type +
                '}';
    }
}
