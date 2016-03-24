package pl.ptm.client.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.ptm.client.api.GeoPointData;
import pl.ptm.client.api.VehicleDistanceData;
import pl.ptm.client.api.VehicleDistanceDataBuilder;
import pl.ptm.client.api.VehiclePositionData;
import pl.ptm.client.service.api.Converter;
import pl.ptm.client.service.api.VehicleCurrentDistanceService;
import pl.ptm.common.service.api.DistanceCalculationService;
import pl.ptm.data.dao.jpa.VehicleCurrentPositionDaoJpa;
import pl.ptm.data.model.VehicleCurrentPositionEntity;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class VehicleCurrentDistanceServiceImpl implements VehicleCurrentDistanceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleCurrentDistanceServiceImpl.class);

    private VehicleCurrentPositionDaoJpa vehicleCurrentPositionDaoJpa;
    private Converter<VehicleCurrentPositionEntity, VehiclePositionData> converter;
    private DistanceCalculationService distanceCalculationService;
    private Comparator<VehicleDistanceData> vehicleDistanceDataComparator;

    public VehicleCurrentDistanceServiceImpl(final VehicleCurrentPositionDaoJpa vehicleCurrentPositionDaoJpa,
                                             final Converter<VehicleCurrentPositionEntity, VehiclePositionData> converter,
                                             final DistanceCalculationService distanceCalculationService,
                                             final Comparator<VehicleDistanceData> vehicleDistanceDataComparator) {
        this.vehicleCurrentPositionDaoJpa = vehicleCurrentPositionDaoJpa;
        this.converter = converter;
        this.distanceCalculationService = distanceCalculationService;
        this.vehicleDistanceDataComparator = vehicleDistanceDataComparator;
    }

    public List<VehicleDistanceData> getVehicleDistanceData(final GeoPointData geoPoint,
                                                            final String providerId,
                                                            final String lineName,
                                                            final double maxDistance) {
        LOGGER.debug("getVehiclePositionData {} {}", providerId, lineName);
        List<VehicleCurrentPositionEntity> positions = vehicleCurrentPositionDaoJpa
                .findByProviderIdAndLineName(providerId, lineName);

        return positions
                .parallelStream()
                .map(position -> converter.convert(position))
                .map(vehiclePositionData -> {
                    double distance = distanceCalculationService.calculateDistanceInMeters(
                            geoPoint.getLongitude(), geoPoint.getLatitude(),
                            vehiclePositionData.getLon(), vehiclePositionData.getLat());

                    return VehicleDistanceDataBuilder.aVehicleDistanceData()
                            .withVehiclePositionData(vehiclePositionData)
                            .withPoint(geoPoint)
                            .withDistance(distance)
                            .build();
                })

                .filter(vehicleDistanceData -> maxDistance >= vehicleDistanceData.getDistance())
                .sorted(vehicleDistanceDataComparator)
                .collect(toList());
    }

    @Override
    public List<VehicleDistanceData> getVehicleDistanceData(GeoPointData geoPoint, String providerId, double maxDistance) {
        List<VehicleCurrentPositionEntity> positions = vehicleCurrentPositionDaoJpa
                .findByProviderId(providerId);

        return positions
                .parallelStream()
                .map(position -> converter.convert(position))
                .map(vehiclePositionData -> {
                    double distance = distanceCalculationService.calculateDistanceInMeters(
                            geoPoint.getLongitude(), geoPoint.getLatitude(),
                            vehiclePositionData.getLon(), vehiclePositionData.getLat());

                    return VehicleDistanceDataBuilder.aVehicleDistanceData()
                            .withVehiclePositionData(vehiclePositionData)
                            .withPoint(geoPoint)
                            .withDistance(distance)
                            .build();
                })

                .filter(vehicleDistanceData -> maxDistance >= vehicleDistanceData.getDistance())
                .sorted(vehicleDistanceDataComparator)
                .collect(toList());
    }
}
