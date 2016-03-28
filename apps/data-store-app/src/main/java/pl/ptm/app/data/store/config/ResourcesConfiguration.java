package pl.ptm.app.data.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.ptm.client.api.VehicleDistanceData;
import pl.ptm.client.api.VehiclePositionData;
import pl.ptm.client.resource.VehicleCurrentDistanceResource;
import pl.ptm.client.resource.VehicleCurrentPositionResource;
import pl.ptm.client.service.api.Converter;
import pl.ptm.client.service.api.VehicleCurrentDistanceService;
import pl.ptm.client.service.api.VehicleCurrentPositionService;
import pl.ptm.client.service.impl.VehicleCurrentDistanceServiceImpl;
import pl.ptm.client.service.impl.VehicleCurrentPositionEntityToVehiclePositionDataConverter;
import pl.ptm.client.service.impl.VehicleCurrentPositionServiceImpl;
import pl.ptm.common.service.api.DistanceCalculationService;
import pl.ptm.data.dao.jpa.VehicleCurrentPositionDaoJpa;
import pl.ptm.data.model.VehicleCurrentPositionEntity;

import java.util.Comparator;


@Configuration
@Import(CommonServicesConfiguration.class)
public class ResourcesConfiguration {

    @Autowired
    private VehicleCurrentPositionDaoJpa vehicleCurrentPositionDaoJpa;

    @Autowired
    private DistanceCalculationService distanceCalculationService;

    @Autowired
    private Comparator<VehicleDistanceData> vehicleDistanceDataComparator;

    @Bean
    public VehicleCurrentPositionResource vehicleCurrentPositionResource() {
        return new VehicleCurrentPositionResource(vehicleCurrentPositionService());
    }

    @Bean
    public VehicleCurrentDistanceResource vehicleCurrentDistanceResource() {
        return new VehicleCurrentDistanceResource(vehicleCurrentDistanceService());
    }

    @Bean
    public VehicleCurrentDistanceService vehicleCurrentDistanceService() {
        return new VehicleCurrentDistanceServiceImpl(vehicleCurrentPositionDaoJpa, converter(),
                distanceCalculationService, vehicleDistanceDataComparator);
    }

    @Bean
    public VehicleCurrentPositionService vehicleCurrentPositionService() {
        return new VehicleCurrentPositionServiceImpl(vehicleCurrentPositionDaoJpa,
                converter());
    }

    @Bean
    public Converter<VehicleCurrentPositionEntity, VehiclePositionData> converter() {
        return new VehicleCurrentPositionEntityToVehiclePositionDataConverter();
    }


}
