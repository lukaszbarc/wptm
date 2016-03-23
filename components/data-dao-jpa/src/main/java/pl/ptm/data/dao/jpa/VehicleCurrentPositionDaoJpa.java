package pl.ptm.data.dao.jpa;

import org.springframework.data.repository.CrudRepository;
import pl.ptm.data.model.VehicleCurrentPositionEntity;

import java.util.List;
import java.util.Optional;

public interface VehicleCurrentPositionDaoJpa extends CrudRepository<VehicleCurrentPositionEntity, Long> {

    Optional<VehicleCurrentPositionEntity> findByProviderIdAndLineNameAndBrigade(final String providerId,
                                                                                 final String lineName,
                                                                                 final int brigade);

    List<VehicleCurrentPositionEntity> findByProviderIdAndLineName(final String providerId,
                                                                   final String lineName);

    List<VehicleCurrentPositionEntity> findByProviderId(final String providerId);
}
