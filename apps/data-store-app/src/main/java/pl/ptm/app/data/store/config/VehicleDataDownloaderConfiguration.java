package pl.ptm.app.data.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.ptm.data.provider.DataProvider;
import pl.ptm.data.service.VehicleDataDownloaderService;
import pl.ptm.data.service.VehicleDataEventPublisher;
import pl.ptm.data.service.impl.VehicleDataDownloaderServiceImpl;
import pl.ptm.data.service.impl.VehicleDataEventPublisherImpl;

import java.util.List;

@Configuration
@Import(VehicleDataProvidersConfiguration.class)
public class VehicleDataDownloaderConfiguration {

    @Autowired
    private List<DataProvider> vehicleDataProviders;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Bean
    public VehicleDataDownloaderService vehicleDataDownloaderService() {
        return new VehicleDataDownloaderServiceImpl(vehicleDataProviders, vehicleDataEventPublisher());
    }

    @Bean
    public VehicleDataEventPublisher vehicleDataEventPublisher() {
        return new VehicleDataEventPublisherImpl(applicationEventPublisher);
    }
}
