package pl.ptm.client.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public @ResponseBody List<VehicleStopData> getStops(){
        return vehicleStopService.getRegisteredVehicleStops();
    }

    @RequestMapping(value = "/nearest", method = RequestMethod.GET)
    public @ResponseBody VehicleStopData getNearest(@RequestParam Double lon,
                                                    @RequestParam Double lat){
        return vehicleStopService.getNearestVehicleStop(lon, lat);
    }
}
