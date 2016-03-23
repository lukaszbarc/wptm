package pl.ptm.app.data.store.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.ptm.data.provider.warsaw.trams.WarsawTramsDataProvider;

@Configuration
public class VehicleDataProvidersConfiguration {

    @Value("${warsaw.trams.rest.endpoint}")
    private String warsawTramsRestEndpoint;

    @Value("${warsaw.trams.rest.providerId}")
    private String providerId;

    @Value("${warsaw.trams.rest.apiKey}")
    private String apiKey;

    @Bean
    public WarsawTramsDataProvider warsawTramsDataProvider() {
        WarsawTramsDataProvider provider = new WarsawTramsDataProvider();
        provider.setWarsawTramDataWsEndpoint(warsawTramsRestEndpoint);
        provider.setWarsawUmApiKey(apiKey);
        provider.setRestTemplate(new RestTemplate());
        provider.setProviderId(providerId);
        return provider;
    }
}
