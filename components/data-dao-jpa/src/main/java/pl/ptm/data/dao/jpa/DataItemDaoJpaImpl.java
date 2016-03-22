package pl.ptm.data.dao.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.ptm.data.model.DataItemEntity;

import java.util.List;

public interface DataItemDaoJpaImpl extends CrudRepository<DataItemEntity, Long> {

    List<DataItemEntity> findByLineAndBrigade(int line, int brigade);

    List<DataItemEntity> findByDataSnapshotIdentity(long identity);

    DataItemEntity findFirst1ByLineAndBrigadeOrderByDataSnapshotIdentityDesc(int line, int brigade);
}
