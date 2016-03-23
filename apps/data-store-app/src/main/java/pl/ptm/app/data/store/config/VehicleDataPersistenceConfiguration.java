package pl.ptm.app.data.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.ptm.data.dao.jpa.DataDaoJpa;
import pl.ptm.data.service.VehicleDataPersistenceService;
import pl.ptm.data.service.impl.VehicleDataPersistenceServiceImpl;


@Configuration
public class VehicleDataPersistenceConfiguration {

    @Autowired
    private DataDaoJpa dataDaoJpa;

    @Bean
    public VehicleDataPersistenceService vehicleDataPersistenceService() {
        return new VehicleDataPersistenceServiceImpl(dataDaoJpa);
    }
}
