package pl.ptm.client.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.ptm.client.api.VehiclePositionData;
import pl.ptm.client.service.api.VehicleCurrentPositionService;

import java.util.List;

@RestController
@RequestMapping("/{providerId}/position/current")
public class VehicleCurrentPositionResource {

    @Autowired
    private VehicleCurrentPositionService vehicleCurrentPositionService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<VehiclePositionData> getVehiclePositionData(@PathVariable("providerId") String providerId) {
        return vehicleCurrentPositionService.getVehiclePositionData(providerId);
    }

    @RequestMapping(path = "/{lineName}", method = RequestMethod.GET)
    public List<VehiclePositionData> getVehiclePositionData(@PathVariable("providerId") String providerId,
                                                            @PathVariable("lineName") String lineName) {
        return vehicleCurrentPositionService.getVehiclePositionData(providerId, lineName);
    }

    @RequestMapping(path = "/{lineName}/{brigadeNumber}", method = RequestMethod.GET)
    public VehiclePositionData getVehiclePositionData(@PathVariable("providerId") String providerId,
                                                      @PathVariable("lineName") String lineName,
                                                      @PathVariable("brigadeNumber") int brigadeNumber) {
        return vehicleCurrentPositionService.getVehiclePositionData(providerId, lineName, brigadeNumber);
    }
}