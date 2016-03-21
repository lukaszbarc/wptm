package pl.ptm.data.provider.warsaw.trams;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class WarsawTramsDataProviderTest {

    private WarsawTramsDataProvider objectUnderTest;


    @Before
    public void init() {
        objectUnderTest = new WarsawTramsDataProvider();
        objectUnderTest.setRestTemplate(new RestTemplate());
        objectUnderTest.setWarsawUmApiKey("1fb555a5-a216-4dcc-a0b9-2a44756d276d");
        objectUnderTest.setWarsawTramDataWsEndpoint("https://api.um.warszawa.pl/api/action/wsstore_get/?id=c7238cfe-8b1f-4c38-bb4a-de386db7e776");
    }

    @Test
    public void test() {
        objectUnderTest.fetchData();
    }


}