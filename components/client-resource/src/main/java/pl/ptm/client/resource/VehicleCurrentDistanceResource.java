package pl.ptm.client.resource;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ptm.client.api.GeoPointDataBuilder;
import pl.ptm.client.api.VehicleDistanceData;
import pl.ptm.client.service.api.VehicleCurrentDistanceService;

import java.util.List;

@RestController
@RequestMapping(value = "/{providerId}/position/current", produces = "application/json; charset=UTF-8")
public class VehicleCurrentDistanceResource {

    private VehicleCurrentDistanceService vehicleCurrentDistanceService;

    public VehicleCurrentDistanceResource(final VehicleCurrentDistanceService vehicleCurrentDistanceService) {
        this.vehicleCurrentDistanceService = vehicleCurrentDistanceService;
    }

    @RequestMapping("/{lineName}/near")
    public List<VehicleDistanceData> getVehicleDistanceData(@PathVariable("providerId") final String providerId,
                                                            @PathVariable("lineName") String lineName,
                                                            @RequestParam("latitude") final double latitude,
                                                            @RequestParam("longitude") final double longitude,
                                                            @RequestParam("maxDistance") double maxDistance) {
        return vehicleCurrentDistanceService.getVehicleDistanceData(GeoPointDataBuilder.aGeoPointData()
                .withElevation(0.0)
                .withLatitude(latitude)
                .withLongitude(longitude)
                .build(), providerId, lineName, maxDistance);
    }

    @RequestMapping("/near")
    public List<VehicleDistanceData> getVehicleDistanceData(@PathVariable("providerId") final String providerId,
                                                            @RequestParam("latitude") final double latitude,
                                                            @RequestParam("longitude") final double longitude,
                                                            @RequestParam("maxDistance") double maxDistance) {
        return vehicleCurrentDistanceService.getVehicleDistanceData(GeoPointDataBuilder.aGeoPointData()
                .withElevation(0.0)
                .withLatitude(latitude)
                .withLongitude(longitude)
                .build(), providerId, maxDistance);
    }
}
