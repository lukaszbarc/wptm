package pl.ptm.data.dao.jpa;

import org.springframework.data.repository.CrudRepository;
import pl.ptm.data.model.DataItemEntity;

import java.util.List;

public interface DataItemDaoJpa extends CrudRepository<DataItemEntity, Long> {

    List<DataItemEntity> findByLineAndBrigade(String line, int brigade);

    List<DataItemEntity> findByDataSnapshotIdentity(long identity);

    DataItemEntity findFirst1ByLineAndBrigadeOrderByDataSnapshotIdentityDesc(String line, int brigade);
}
