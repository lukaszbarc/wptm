package pl.ptm.app.data.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.ptm.data.provider.warsaw.trams.WarsawTramsDataProvider;

@Configuration
public class VehicleDataProvidersConfiguration {

    @Bean
    public WarsawTramsDataProvider warsawTramsDataProvider() {
        WarsawTramsDataProvider provider = new WarsawTramsDataProvider();
        provider.setWarsawTramDataWsEndpoint("https://api.um.warszawa.pl/api/action/wsstore_get/?id=c7238cfe-8b1f-4c38-bb4a-de386db7e776");
        provider.setWarsawUmApiKey("1fb555a5-a216-4dcc-a0b9-2a44756d276d");
        provider.setRestTemplate(new RestTemplate());
        provider.setProviderId("warsaw-trams");
        return provider;
    }
}
