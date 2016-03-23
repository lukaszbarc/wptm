package pl.ptm.data.dao.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.ptm.data.model.DataSnapshotEntity;

public interface DataDaoJpa extends CrudRepository<DataSnapshotEntity, Long> {

    @Query("SELECT max(t.identity) FROM #{#entityName} t")
    Long getMaxId();


}
