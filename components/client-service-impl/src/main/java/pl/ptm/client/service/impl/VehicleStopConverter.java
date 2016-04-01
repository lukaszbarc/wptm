package pl.ptm.client.service.impl;

import org.springframework.stereotype.Component;
import pl.ptm.client.api.VehicleStopData;
import pl.ptm.client.service.api.BiDirectionalConverter;
import pl.ptm.data.model.VehicleStopEntity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by jbogacz on 2016-04-01.
 */
@Component
public class VehicleStopConverter implements BiDirectionalConverter<VehicleStopEntity, VehicleStopData> {

    @Override
    public VehicleStopData toRight(VehicleStopEntity vehicleStopEntity) {
        VehicleStopData converted = new VehicleStopData();
        converted.setName(vehicleStopEntity.getName());
        converted.setDest(vehicleStopEntity.getDest());
        converted.setLat(vehicleStopEntity.getLat());
        converted.setLon(vehicleStopEntity.getLon());
        return converted;
    }

    @Override
    public VehicleStopEntity toLeft(VehicleStopData vehicleStopData) {
        throw new NotImplementedException();
    }
}
