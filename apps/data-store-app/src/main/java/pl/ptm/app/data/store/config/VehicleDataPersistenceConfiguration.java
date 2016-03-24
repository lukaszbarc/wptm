package pl.ptm.app.data.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.ptm.common.service.api.BearingCalculationService;
import pl.ptm.common.service.api.DistanceCalculationService;
import pl.ptm.common.service.api.SpeedCalculationService;
import pl.ptm.data.dao.jpa.DataDaoJpa;
import pl.ptm.data.dao.jpa.VehicleCurrentPositionDaoJpa;
import pl.ptm.data.service.VehicleCurrentPositionPersistenceService;
import pl.ptm.data.service.VehicleDataPersistenceService;
import pl.ptm.data.service.impl.VehicleCurrentPositionPersistenceServiceImpl;
import pl.ptm.data.service.impl.VehicleDataPersistenceServiceImpl;


@Configuration
@Import(CommonServicesConfiguration.class)
public class VehicleDataPersistenceConfiguration {

    @Autowired
    private DataDaoJpa dataDaoJpa;

    @Autowired
    private VehicleCurrentPositionDaoJpa vehicleCurrentPositionDaoJpa;

    @Autowired
    private DistanceCalculationService distanceCalculationService;

    @Autowired
    private SpeedCalculationService speedCalculationService;

    @Autowired
    private BearingCalculationService bearingCalculationService;

    @Bean
    public VehicleDataPersistenceService vehicleDataPersistenceService() {
        return new VehicleDataPersistenceServiceImpl(dataDaoJpa);
    }

    @Bean
    public VehicleCurrentPositionPersistenceService vehicleCurrentPositionPersistenceService() {
        return new VehicleCurrentPositionPersistenceServiceImpl(vehicleCurrentPositionDaoJpa,
                distanceCalculationService,
                speedCalculationService,
                bearingCalculationService);
    }
}
