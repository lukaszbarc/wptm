package pl.ptm.data.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.ptm.data.provider.DataProvider;
import pl.ptm.data.provider.dto.DataSnapshotDTO;
import pl.ptm.data.service.VehicleDataDownloaderService;
import pl.ptm.data.service.VehicleDataEventPublisher;

import java.util.List;

public class VehicleDataDownloaderServiceImpl implements VehicleDataDownloaderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleDataDownloaderServiceImpl.class);

    private List<DataProvider> dataProviders;

    private VehicleDataEventPublisher eventPublisher;

    public VehicleDataDownloaderServiceImpl(List<DataProvider> dataProviders, VehicleDataEventPublisher eventPublisher) {
        this.dataProviders = dataProviders;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void fetchVehicleData() {
        for (DataProvider dataProvider : dataProviders) {
            LOGGER.debug("Fetching new vehicle data from : {}", dataProvider.name());
            DataSnapshotDTO data = dataProvider.fetchData();
            if (!data.getItems().isEmpty()) {
                LOGGER.info("Fetch {} vehicle data from : {}", data.getItems().size(), dataProvider.name());
                eventPublisher.publishEvent(data);
            } else {
                LOGGER.warn("No vehicle data found in response from : {}", dataProvider.name());
            }
        }

    }
}
