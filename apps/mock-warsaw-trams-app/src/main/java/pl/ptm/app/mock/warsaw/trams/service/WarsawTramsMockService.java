package pl.ptm.app.mock.warsaw.trams.service;


import pl.ptm.data.provider.warsaw.trams.dto.WarsawTramDataSnapshotDTO;

@FunctionalInterface
public interface WarsawTramsMockService {
    WarsawTramDataSnapshotDTO provideNextPreparedData();
}
