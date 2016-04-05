package pl.ptm.client.resource;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.core.Is.is;

/**
 * Created by jbogacz on 2016-04-05.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = DataStoreApplication.class)
//@WebAppConfiguration
//@IntegrationTest
public class VehicleStopResourceTest {

//    @Test
    public void testGetStops() throws Exception {

    }

//    @Test
    public void testGetNearest() throws Exception {
        expect().
            statusCode(200).
                body(
                    "lat", is(21.043983f),
                    "lon", is(52.248998f),
                    "name", is("TARGOWA"),
                    "dest", is("AL.ZIELENIECKA")).
        when().
            get("/stops/nearest?lon=21.028883457183838&lat=52.24882376803035");
    }
}