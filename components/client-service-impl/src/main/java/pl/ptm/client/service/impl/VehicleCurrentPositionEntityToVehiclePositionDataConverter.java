package pl.ptm.client.service.impl;

import com.grum.geocalc.DegreeCoordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;
import pl.ptm.client.api.VehiclePositionData;
import pl.ptm.client.service.api.Converter;
import pl.ptm.data.model.VehicleCurrentPositionEntity;

import static pl.ptm.client.api.VehiclePositionDataBuilder.aVehiclePositionData;


public class VehicleCurrentPositionEntityToVehiclePositionDataConverter implements
        Converter<VehicleCurrentPositionEntity, VehiclePositionData> {

    @Override
    public VehiclePositionData convert(VehicleCurrentPositionEntity position) {
        Point point = EarthCalc.pointRadialDistance(
                new Point(
                        new DegreeCoordinate(position.getCurrentLat()),
                        new DegreeCoordinate(position.getCurrentLon())
                ),
                position.getBearing(),
                position.getCalculatedSpeed() * 5.0);

        return aVehiclePositionData()
                .withProviderId(position.getProviderId())
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
