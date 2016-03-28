package pl.ptm.data.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import pl.ptm.data.dao.jpa.DataDaoJpa;
import pl.ptm.data.model.DataItemEntity;
import pl.ptm.data.model.DataSnapshotEntity;
import pl.ptm.data.provider.dto.DataSnapshotDTO;
import pl.ptm.data.service.VehicleDataPersistenceService;
import pl.ptm.data.service.events.NewDataSnapshotEvent;

import java.util.stream.Collectors;

public class VehicleDataPersistenceServiceImpl implements VehicleDataPersistenceService, ApplicationListener<NewDataSnapshotEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleDataPersistenceServiceImpl.class);

    private DataDaoJpa dataDaoJpa;

    public VehicleDataPersistenceServiceImpl(final DataDaoJpa dataDaoJpa) {
        this.dataDaoJpa = dataDaoJpa;
    }

    @Override
    public long save(DataSnapshotDTO dataSnapshotDTO) {
        LOGGER.debug("Save new data snapshot for {} with {} vehicles data",
                dataSnapshotDTO.getProviderId(),
                dataSnapshotDTO.getItems().size());

        final DataSnapshotEntity dataSnapshot = new DataSnapshotEntity();
        dataSnapshot.setDate(dataSnapshotDTO.getDate());
        dataSnapshot.setProviderId(dataSnapshotDTO.getProviderId());
        dataSnapshot.setItems(dataSnapshotDTO.getItems().stream().map(dataItemDTO -> {
            DataItemEntity di = new DataItemEntity();
            di.setDate(dataItemDTO.getDate());
            di.setFirstLine(String.valueOf(dataItemDTO.getFirstLine()));
            di.setBrigade(dataItemDTO.getBrigade());
            di.setLat(dataItemDTO.getLat());
            di.setLon(dataItemDTO.getLon());
            di.setLine(String.valueOf(dataItemDTO.getLine()));
            di.setStatus(dataItemDTO.getStatus());
            di.setDataSnapshot(dataSnapshot);
            return di;
        }).collect(Collectors.toList()));
        DataSnapshotEntity saved = dataDaoJpa.save(dataSnapshot);
        LOGGER.debug("New data snapshot for {} with {} vehicles data saved with id {}",
                dataSnapshotDTO.getProviderId(),
                dataSnapshotDTO.getItems().size(),
                saved.getIdentity());

        return saved.getIdentity();
    }

    @Override
    public void onApplicationEvent(NewDataSnapshotEvent event) {
        LOGGER.debug("Event received");
        save(event.getDataSnapshotDTO());
    }
}
