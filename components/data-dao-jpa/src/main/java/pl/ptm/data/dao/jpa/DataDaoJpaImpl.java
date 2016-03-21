package pl.ptm.data.dao.jpa;

import org.springframework.data.repository.Repository;
import pl.ptm.data.model.DataSnapshotEntity;

public interface DataDaoJpaImpl extends Repository<DataSnapshotEntity, Long> {
}
