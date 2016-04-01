package pl.ptm.app.data.store.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.ptm.data.service.VehicleStopsService;

import javax.annotation.PostConstruct;

/**
 * Created by jbogacz on 2016-04-01.
 */
@Component
public class VehicleStopsDataDownloadTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleStopsDataDownloadTask.class);

    @Autowired
    private VehicleStopsService vehicleStopsService;

    @Scheduled(cron = "0 0 12 * * ?")
    public void synchronize(){
        try {
            LOGGER.info("Start synchronization ZTM data");
            vehicleStopsService.synchronizeFtpData();
        } catch (Exception e) {
            LOGGER.error("Error during ztm data synchronization", e);
        }
    }

    @PostConstruct
    public void postConstruct(){
        synchronize();
    }

}
