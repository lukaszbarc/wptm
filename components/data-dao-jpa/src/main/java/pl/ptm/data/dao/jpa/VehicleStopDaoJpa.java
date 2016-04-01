package pl.ptm.data.dao.jpa;

import org.springframework.data.repository.CrudRepository;
import pl.ptm.data.model.VehicleStopEntity;

/**
 * Created by jbogacz on 2016-04-01.
 */
public interface VehicleStopDaoJpa extends CrudRepository<VehicleStopEntity, Long> {
}
