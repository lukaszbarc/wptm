package pl.ptm.components.oauth2.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.ptm.components.oauth2.OAuth2Configuration;
import pl.ptm.components.oauth2.model.AccessToken;
import pl.ptm.components.oauth2.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by JBOGACZ on 2016-04-25.
 */
@Controller
public class ClientController {

    @Autowired
    private OAuth2Configuration oAuth2Configuration;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/client")
    public String  redirectToFbLoginPage() throws IOException {
        return "redirect:" + getLoginRedirectURL();
    }

    @RequestMapping(value = "/auth", params="code")
    public String handleLoginProcess(HttpServletResponse response, String code) throws IOException {
        setAccessTokenInHeader(response, code);
        return "redirect:" + oAuth2Configuration.getRedirectFrontAppUri();
    }

    private void setAccessTokenInHeader(HttpServletResponse response, String code) throws IOException {
        AccessToken accessToken = userService.processUserLogin(code);
        response.setHeader("Authorization", "bearer " + accessToken.getAccessToken());
    }

    public String getLoginRedirectURL() {
        return oAuth2Configuration.getUserAuthorizationUri()+ "?client_id=" +
                oAuth2Configuration.getClientId() + "&display=page&redirect_uri=" +
                oAuth2Configuration.getRedirectClientUri() + "&scope=" + StringUtils.join(oAuth2Configuration.getPerms(), ",");
    }
}
