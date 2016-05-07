package pl.ptm.components.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ptm.components.oauth2.FacebookHttpClient;
import pl.ptm.components.oauth2.model.AccessToken;

import java.io.IOException;

/**
 * Created by JBOGACZ on 2016-04-26.
 */
@Service
public class UserService {

    @Autowired
    private FacebookHttpClient facebookHttpClient;

    public AccessToken processUserLogin(String authToken) throws IOException {
        AccessToken accessToken = facebookHttpClient.getAccessToken(authToken);
        return accessToken;
    }

}
