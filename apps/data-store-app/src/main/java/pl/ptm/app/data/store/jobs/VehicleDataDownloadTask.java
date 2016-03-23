package pl.ptm.app.data.store.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.Scheduled;
import pl.ptm.app.data.store.config.VehicleDataDownloaderConfiguration;
import pl.ptm.data.service.VehicleDataDownloaderService;

/**
 * Created by lbarc on 2016-03-23.
 */
@Configuration
@Import(VehicleDataDownloaderConfiguration.class)
public class VehicleDataDownloadTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleDataDownloadTask.class);

    @Autowired
    private VehicleDataDownloaderService vehicleDataDownloaderService;

    @Scheduled(fixedRate = 30000)
    public void download() {
        LOGGER.debug("Executing task");
        vehicleDataDownloaderService.fetchVehicleData();
        LOGGER.debug("Task execution ended");
    }
}
