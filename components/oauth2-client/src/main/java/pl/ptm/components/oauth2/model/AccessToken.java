package pl.ptm.components.oauth2.model;

import lombok.Data;

import java.util.Map;

/**
 * Created by JBOGACZ on 2016-04-26.
 */
@Data
public class AccessToken {

    public static final String ACCESS_TOKEN = "access_token";
    public static final String EXPIRES = "expires";

    private String accessToken;
    private Long expires;

    public AccessToken(Map<String, String> keyValue){
        accessToken = keyValue.get(ACCESS_TOKEN);
        expires = Long.valueOf(keyValue.get(EXPIRES));
    }
}
