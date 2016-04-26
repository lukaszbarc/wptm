package pl.ptm.components.oauth2;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by JBOGACZ on 2016-04-25.
 */
@Component
@Data
public class OAuth2Configuration {

    @Value("${oauth2.client.clientId}")
    private String clientId;

    @Value("${oauth2.client.redirectClientUri}")
    private String redirectClientUri;

    @Value("${oauth2.client.redirectFrontAppUri}")
    private String redirectFrontAppUri;

    @Value("${oauth2.client.clientSecret}")
    private String clientSecret;

    @Value("${oauth2.client.accessTokenUri}")
    private String accessTokenUri;

    @Value("${oauth2.client.userAuthorizationUri}")
    private String userAuthorizationUri;


    private String[] perms = new String[] {"email"};
}
