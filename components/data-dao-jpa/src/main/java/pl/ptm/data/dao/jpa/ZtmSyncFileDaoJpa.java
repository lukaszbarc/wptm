package pl.ptm.data.dao.jpa;

import org.springframework.data.repository.CrudRepository;
import pl.ptm.data.model.ZtmSyncFileEntity;

/**
 * Created by JBOGACZ on 2016-04-06.
 */
public interface ZtmSyncFileDaoJpa extends CrudRepository<ZtmSyncFileEntity, Long> {

    ZtmSyncFileEntity findByName(String name);

}
