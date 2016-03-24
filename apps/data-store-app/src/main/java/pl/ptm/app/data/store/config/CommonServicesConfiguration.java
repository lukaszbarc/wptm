package pl.ptm.app.data.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.ptm.client.api.VehicleDistanceData;
import pl.ptm.client.api.VehicleDistanceDataComparator;
import pl.ptm.common.service.api.BearingCalculationService;
import pl.ptm.common.service.api.DistanceCalculationService;
import pl.ptm.common.service.api.SpeedCalculationService;
import pl.ptm.common.service.impl.BearingCalculationServiceImpl;
import pl.ptm.common.service.impl.DistanceCalculationServiceImpl;
import pl.ptm.common.service.impl.SpeedCalculationServiceImpl;

import java.util.Comparator;

@Configuration
public class CommonServicesConfiguration {


    @Bean
    public DistanceCalculationService distanceCalculationService() {
        return new DistanceCalculationServiceImpl();
    }

    @Bean
    public SpeedCalculationService speedCalculationService() {
        return new SpeedCalculationServiceImpl();
    }

    @Bean
    public BearingCalculationService bearingCalculationService() {
        return new BearingCalculationServiceImpl();
    }

    @Bean
    public Comparator<VehicleDistanceData> vehicleDistanceDataComparator() {
        return new VehicleDistanceDataComparator();
    }
}
