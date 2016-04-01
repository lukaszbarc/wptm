package pl.ptm.client.resource;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.ptm.client.api.VehiclePositionData;
import pl.ptm.client.service.api.VehicleCurrentPositionService;

import java.util.List;

@RestController
@RequestMapping(value = "/{providerId}/position/current", produces = "application/json; charset=UTF-8")
public class VehicleCurrentPositionResource {

    private VehicleCurrentPositionService vehicleCurrentPositionService;

    public VehicleCurrentPositionResource(final VehicleCurrentPositionService vehicleCurrentPositionService) {
        this.vehicleCurrentPositionService = vehicleCurrentPositionService;
    }

    @ApiOperation(value = "Get all vehicles by provider id", nickname = "getAllVehiclesByProviderId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "providerId", value = "Provider Id", required = true, dataType = "string", paramType = "path", allowableValues = "warsaw-trams,warsaw-trams-mock")
    })
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<VehiclePositionData> getVehiclePositionData(@PathVariable("providerId") String providerId) {
        return vehicleCurrentPositionService.getVehiclePositionData(providerId);
    }

    @ApiOperation(value = "Get vehicles by provider id and line name", nickname = "getVehiclesByProviderIdAndLineName")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "providerId", value = "Provider Id", required = true, dataType = "string", paramType = "path", allowableValues = "warsaw-trams,warsaw-trams-mock"),
            @ApiImplicitParam(name = "lineName", value = "Line name", required = true, dataType = "string", paramType = "path", allowableValues = "8,20,24"),
    })
    @RequestMapping(path = "/{lineName}", method = RequestMethod.GET)
    public List<VehiclePositionData> getVehiclePositionData(@PathVariable("providerId") String providerId,
                                                            @PathVariable("lineName") String lineName) {
        return vehicleCurrentPositionService.getVehiclePositionData(providerId, lineName);
    }

    @ApiOperation(value = "Get vehicles by provider id, line name and brigade number", nickname = "getVehiclesByProviderIdAndLineNameAndBrigade")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "providerId", value = "Provider Id", required = true, dataType = "string", paramType = "path", allowableValues = "warsaw-trams,warsaw-trams-mock"),
            @ApiImplicitParam(name = "lineName", value = "Line name", required = true, dataType = "string", paramType = "path", allowableValues = "8,20,24"),
            @ApiImplicitParam(name = "brigadeNumber", value = "Brigade number", required = true, dataType = "integer", paramType = "path", allowableValues = "1,2,3,4,5,6,7,8,9,10,11,12")
    })
    @RequestMapping(path = "/{lineName}/{brigadeNumber}", method = RequestMethod.GET)
    public VehiclePositionData getVehiclePositionData(@PathVariable("providerId") String providerId,
                                                      @PathVariable("lineName") String lineName,
                                                      @PathVariable("brigadeNumber") int brigadeNumber) {
        return vehicleCurrentPositionService.getVehiclePositionData(providerId, lineName, brigadeNumber);
    }
}
