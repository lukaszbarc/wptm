package pl.ptm.client.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.ptm.client.api.VehicleStopData;
import pl.ptm.client.service.api.VehicleStopService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JBOGACZ on 2016-03-29.
 */
@RestController
@RequestMapping(value = "/stops", produces = "application/json; charset=UTF-8")
public class VehicleStopResource {

    @Autowired
    private VehicleStopService vehicleStopService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<VehicleStopData> retrieveStops(){
        return vehicleStopService.getRegisteredVehicleStops();
    }

}
