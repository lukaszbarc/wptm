package pl.ptm.data.service;

import pl.ptm.data.provider.dto.DataSnapshotDTO;

public interface VehicleDataEventPublisher {

    void publishEvent(DataSnapshotDTO dataSnapshotDTO);
}
