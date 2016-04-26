package pl.ptm.components.oauth2;

import com.google.common.base.Splitter;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.ptm.components.oauth2.model.AccessToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by JBOGACZ on 2016-04-26.
 */
@Component
public class FacebookHttpClient {

    @Autowired
    private OAuth2Configuration oAuth2Configuration;

    public AccessToken getAccessToken(String authToken) throws IOException {
        HttpResponse httpResponse = postForAccessToken(authToken);
        String responseContent = getResponseContent(httpResponse);
        return new AccessToken(
                splitToMap(responseContent));
    }

    private HttpResponse postForAccessToken(String authToken) throws IOException {
        HttpPost post = new HttpPost(
                oAuth2Configuration.getAccessTokenUri());
        post.setEntity(new UrlEncodedFormEntity(
                        buildAccessTokenRequestParameters(authToken)));
        return HttpClientBuilder.create().build().
                execute(post);
    }

    private List<NameValuePair> buildAccessTokenRequestParameters(String authToken) {
        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("code", authToken));
        pairs.add(new BasicNameValuePair("client_id", oAuth2Configuration.getClientId()));
        pairs.add(new BasicNameValuePair("client_secret", oAuth2Configuration.getClientSecret()));
        pairs.add(new BasicNameValuePair("redirect_uri", oAuth2Configuration.getRedirectClientUri()));
        pairs.add(new BasicNameValuePair("grant_type", "authorization_code"));
        return pairs;
    }

    private String getResponseContent(HttpResponse httpResponse) throws IOException {
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(httpResponse.getEntity().getContent()));
        String line;
        StringBuffer result = new StringBuffer();
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    private Map<String, String> splitToMap(String in) {
        return Splitter.on("&").withKeyValueSeparator("=").split(in);
    }
}
