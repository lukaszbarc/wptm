package pl.ptm.client.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ptm.client.api.VehicleStopData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JBOGACZ on 2016-03-29.
 */
@RestController
@RequestMapping(value = "/stops", produces = "application/json; charset=UTF-8")
public class TramsStopsResource {

    public List<VehicleStopData> retrieveStops(){
        VehicleStopData vehicleStopData = new VehicleStopData();
        vehicleStopData.setLon(52.248678);
        vehicleStopData.setLat(21.044226);
        vehicleStopData.setDest("AL.ZIELENIECKA");
        vehicleStopData.setName("TARGOWA");

        List<VehicleStopData> mockList = new ArrayList<>();
        mockList.add(vehicleStopData);

        return mockList;

    }

}
