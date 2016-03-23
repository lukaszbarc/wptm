package pl.ptm.data.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import pl.ptm.data.dao.jpa.VehicleCurrentPositionDaoJpa;
import pl.ptm.data.model.VehicleCurrentPositionEntity;
import pl.ptm.data.provider.dto.DataItemDTO;
import pl.ptm.data.provider.dto.DataSnapshotDTO;
import pl.ptm.data.service.BearingCalculationService;
import pl.ptm.data.service.DistanceCalculationService;
import pl.ptm.data.service.SpeedCalculationService;
import pl.ptm.data.service.VehicleCurrentPositionPersistenceService;
import pl.ptm.data.service.events.NewDataSnapshotEvent;

import java.util.Optional;


public class VehicleCurrentPositionPersistenceServiceImpl implements VehicleCurrentPositionPersistenceService,
        ApplicationListener<NewDataSnapshotEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleCurrentPositionPersistenceServiceImpl.class);

    private VehicleCurrentPositionDaoJpa vehicleCurrentPositionDaoJpa;

    private DistanceCalculationService distanceCalculationService;

    private SpeedCalculationService speedCalculationService;
    private BearingCalculationService bearingCalculationService;

    public VehicleCurrentPositionPersistenceServiceImpl(VehicleCurrentPositionDaoJpa vehicleCurrentPositionDaoJpa,
                                                        DistanceCalculationService distanceCalculationService,
                                                        SpeedCalculationService speedCalculationService,
                                                        BearingCalculationService bearingCalculationService) {
        this.vehicleCurrentPositionDaoJpa = vehicleCurrentPositionDaoJpa;
        this.distanceCalculationService = distanceCalculationService;
        this.speedCalculationService = speedCalculationService;
        this.bearingCalculationService = bearingCalculationService;
    }

    @Override
    public long save(DataSnapshotDTO dataSnapshotDTO) {
        for (DataItemDTO dataItemDTO : dataSnapshotDTO.getItems()) {
            Optional<VehicleCurrentPositionEntity> vehicleCurrentPositionEntityOpt = vehicleCurrentPositionDaoJpa
                    .findByProviderIdAndLineNameAndBrigade(
                            dataSnapshotDTO.getProviderId(),
                            String.valueOf(dataItemDTO.getLine()),
                            dataItemDTO.getBrigade()
                    );
            VehicleCurrentPositionEntity currentEntity;
            boolean calculateSpeed = false;
            if (vehicleCurrentPositionEntityOpt.isPresent()) {
                LOGGER.trace("Found last position for {} line {} brigade {}", dataSnapshotDTO.getProviderId(),
                        dataItemDTO.getLine(), dataItemDTO.getBrigade());
                currentEntity = vehicleCurrentPositionEntityOpt.get();
                currentEntity.setLastLat(currentEntity.getCurrentLat());
                currentEntity.setLastLon(currentEntity.getCurrentLon());
                currentEntity.setLastPositionDate(currentEntity.getCurrentPositionDate());
                calculateSpeed = true;
            } else {
                LOGGER.debug("Last position for {} line {} brigade {} not found, create new one",
                        dataSnapshotDTO.getProviderId(),
                        dataItemDTO.getLine(), dataItemDTO.getBrigade());
                currentEntity = new VehicleCurrentPositionEntity();
                currentEntity.setProviderId(dataSnapshotDTO.getProviderId());
                currentEntity.setLineName(String.valueOf(dataItemDTO.getLine()));
                currentEntity.setBrigade(dataItemDTO.getBrigade());
            }
            currentEntity.setCurrentLat(dataItemDTO.getLat());
            currentEntity.setCurrentLon(dataItemDTO.getLon());
            currentEntity.setCurrentPositionDate(dataItemDTO.getDate());

            if (calculateSpeed) {
                double distance = distanceCalculationService.calculateDistanceInMeters(currentEntity.getLastLon(),
                        currentEntity.getLastLat(), currentEntity.getCurrentLon(), currentEntity.getCurrentLat());
                double deltaTime = (currentEntity.getCurrentPositionDate().getTime() - currentEntity.getLastPositionDate().getTime()) / 1000.0;
                double speed = speedCalculationService.calculateSpeedInKph(distance, deltaTime);
                currentEntity.setCalculatedSpeed(speed);

                double bearing = bearingCalculationService.calculateBearing(currentEntity.getLastLon(),
                        currentEntity.getLastLat(), currentEntity.getCurrentLon(), currentEntity.getCurrentLat());
                currentEntity.setBearing(bearing);

            }
            vehicleCurrentPositionDaoJpa.save(currentEntity);
        }
        return 0;
    }


    @Override
    public void onApplicationEvent(NewDataSnapshotEvent event) {
        LOGGER.debug("Event received");
        save(event.getDataSnapshotDTO());
    }

}
