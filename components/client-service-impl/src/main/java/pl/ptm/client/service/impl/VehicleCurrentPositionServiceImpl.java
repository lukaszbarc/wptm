package pl.ptm.client.service.impl;

import com.grum.geocalc.DegreeCoordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ptm.client.api.VehiclePositionData;
import pl.ptm.client.service.api.VehicleCurrentPositionService;
import pl.ptm.client.service.impl.exception.VehicleNotFoundException;
import pl.ptm.data.dao.jpa.VehicleCurrentPositionDaoJpa;
import pl.ptm.data.model.VehicleCurrentPositionEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static pl.ptm.client.api.VehiclePositionDataBuilder.aVehiclePositionData;


@Service
public class VehicleCurrentPositionServiceImpl implements VehicleCurrentPositionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleCurrentPositionServiceImpl.class);

    @Autowired
    private VehicleCurrentPositionDaoJpa vehicleCurrentPositionDaoJpa;

    @Override
    public VehiclePositionData getVehiclePositionData(String providerId, String lineName, int brigadeNumber) {
        Optional<VehicleCurrentPositionEntity> positionOptional = vehicleCurrentPositionDaoJpa
                .findByProviderIdAndLineNameAndBrigade(providerId, lineName, brigadeNumber);

        if (positionOptional.isPresent()) {
            VehicleCurrentPositionEntity position = positionOptional.get();
            return new Converter(providerId, position).invoke();
        } else {
            throw new VehicleNotFoundException();
        }
    }

    @Override
    public List<VehiclePositionData> getVehiclePositionData(String providerId, String lineName) {
        List<VehicleCurrentPositionEntity> positions = vehicleCurrentPositionDaoJpa
                .findByProviderIdAndLineName(providerId, lineName);

        return positions.stream().map(position -> new Converter(providerId, position).invoke()).collect(Collectors.toList());
    }

    @Override
    public List<VehiclePositionData> getVehiclePositionData(String providerId) {

        List<VehicleCurrentPositionEntity> positions = vehicleCurrentPositionDaoJpa
                .findByProviderId(providerId);

        return positions.stream().map(position -> new Converter(providerId, position).invoke()).collect(Collectors.toList());
    }

    private class Converter {
        private String providerId;
        private VehicleCurrentPositionEntity position;

        public Converter(String providerId, VehicleCurrentPositionEntity position) {
            this.providerId = providerId;
            this.position = position;
        }

        public VehiclePositionData invoke() {
            Point point = EarthCalc.pointRadialDistance(
                    new Point(
                            new DegreeCoordinate(position.getCurrentLat()),
                            new DegreeCoordinate(position.getCurrentLon())
                    ),
                    position.getBearing(),
                    position.getCalculatedSpeed() * 5.0);

            return aVehiclePositionData()
                    .withProviderId(providerId)
                    .withShortName(position.getLineName())
                    .withFullName(position.getLineName() + "-" + position.getBrigade()
                            + " v: " + position.getCalculatedSpeed() + " kph"
                            + " course: " + position.getBearing())
                    .withLat(position.getCurrentLat())
                    .withLon(position.getCurrentLon())
                    .withNextLat(point.getLatitude())
                    .withNextLon(point.getLongitude())
                    .withCalculatedSpeed(position.getCalculatedSpeed())
                    .withBearing(position.getBearing())
                    .build();
        }

    }
}
