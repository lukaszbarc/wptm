package pl.ptm.components.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by JBOGACZ on 2016-04-25.
 */
@SpringBootApplication(scanBasePackages = "pl.ptm.components.oauth2")
public class OAuth2ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2ClientApplication.class, args);
    }
}
