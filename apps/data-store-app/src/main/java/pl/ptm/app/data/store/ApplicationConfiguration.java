package pl.ptm.app.data.store;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import pl.ptm.data.provider.warsaw.trams.WarsawTramsDataProvider;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public WarsawTramsDataProvider warsawTramsDataProvider() {
        WarsawTramsDataProvider provider = new WarsawTramsDataProvider();
        provider.setWarsawTramDataWsEndpoint("https://api.um.warszawa.pl/api/action/wsstore_get/?id=c7238cfe-8b1f-4c38-bb4a-de386db7e776");
        provider.setWarsawUmApiKey("1fb555a5-a216-4dcc-a0b9-2a44756d276d");
        provider.setRestTemplate(new RestTemplate());
        provider.setProviderId("warsaw-trams");
        return provider;
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        // return new CorsFilter(source);
        final FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
