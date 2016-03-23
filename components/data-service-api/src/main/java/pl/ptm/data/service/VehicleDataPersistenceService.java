package pl.ptm.data.service;

import pl.ptm.data.provider.dto.DataSnapshotDTO;

/**
 * Created by lbarc on 2016-03-23.
 */
public interface VehicleDataPersistenceService {


    long save(DataSnapshotDTO dataSnapshotDTO);
}
