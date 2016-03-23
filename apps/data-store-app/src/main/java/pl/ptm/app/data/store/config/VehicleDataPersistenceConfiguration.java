package pl.ptm.app.data.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.ptm.data.dao.jpa.DataDaoJpa;
import pl.ptm.data.dao.jpa.VehicleCurrentPositionDaoJpa;
import pl.ptm.data.service.*;
import pl.ptm.data.service.impl.*;


@Configuration
public class VehicleDataPersistenceConfiguration {

    @Autowired
    private DataDaoJpa dataDaoJpa;

    @Autowired
    private VehicleCurrentPositionDaoJpa vehicleCurrentPositionDaoJpa;

    @Bean
    public VehicleDataPersistenceService vehicleDataPersistenceService() {
        return new VehicleDataPersistenceServiceImpl(dataDaoJpa);
    }

    @Bean
    public VehicleCurrentPositionPersistenceService vehicleCurrentPositionPersistenceService() {
        return new VehicleCurrentPositionPersistenceServiceImpl(vehicleCurrentPositionDaoJpa,
                distanceCalculationService(),
                speedCalculationService(),
                bearingCalculationService());
    }

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
}
