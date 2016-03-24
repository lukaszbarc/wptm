package pl.ptm.client.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.ptm.client.api.VehiclePositionData;
import pl.ptm.client.service.api.Converter;
import pl.ptm.client.service.api.VehicleCurrentPositionService;
import pl.ptm.client.service.impl.exception.VehicleNotFoundException;
import pl.ptm.data.dao.jpa.VehicleCurrentPositionDaoJpa;
import pl.ptm.data.model.VehicleCurrentPositionEntity;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


public class VehicleCurrentPositionServiceImpl implements VehicleCurrentPositionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleCurrentPositionServiceImpl.class);

    private VehicleCurrentPositionDaoJpa vehicleCurrentPositionDaoJpa;
    private Converter<VehicleCurrentPositionEntity, VehiclePositionData> converter;

    public VehicleCurrentPositionServiceImpl(VehicleCurrentPositionDaoJpa vehicleCurrentPositionDaoJpa,
                                             Converter<VehicleCurrentPositionEntity, VehiclePositionData> converter) {
        this.vehicleCurrentPositionDaoJpa = vehicleCurrentPositionDaoJpa;
        this.converter = converter;
    }

    @Override
    public VehiclePositionData getVehiclePositionData(String providerId, String lineName, int brigadeNumber) {
        LOGGER.debug("getVehiclePositionData {} {} {}", providerId, lineName, brigadeNumber);
        Optional<VehicleCurrentPositionEntity> positionOptional = vehicleCurrentPositionDaoJpa
                .findByProviderIdAndLineNameAndBrigade(providerId, lineName, brigadeNumber);

        if (positionOptional.isPresent()) {
            VehicleCurrentPositionEntity position = positionOptional.get();
            return converter.convert(position);
        } else {
            throw new VehicleNotFoundException();
        }
    }

    @Override
    public List<VehiclePositionData> getVehiclePositionData(String providerId, String lineName) {
        LOGGER.debug("getVehiclePositionData {} {}", providerId, lineName);
        List<VehicleCurrentPositionEntity> positions = vehicleCurrentPositionDaoJpa
                .findByProviderIdAndLineName(providerId, lineName);

        return positions
                .stream()
                .map(position -> converter.convert(position))
                .collect(toList());
    }

    @Override
    public List<VehiclePositionData> getVehiclePositionData(String providerId) {
        LOGGER.debug("getVehiclePositionData {}", providerId);
        List<VehicleCurrentPositionEntity> positions = vehicleCurrentPositionDaoJpa
                .findByProviderId(providerId);

        return positions
                .stream()
                .map(position -> converter.convert(position))
                .collect(toList());
    }

}
