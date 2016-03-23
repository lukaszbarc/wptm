package pl.ptm.data.service;

import pl.ptm.data.provider.dto.DataSnapshotDTO;

public interface VehicleCurrentPositionPersistenceService {

    long save(DataSnapshotDTO dataSnapshotDTO);
}
