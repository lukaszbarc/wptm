package pl.ptm.app.data.store;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "pl.ptm")
@EnableScheduling
@EnableJpaRepositories(basePackages = "pl.ptm.data.dao.jpa")
@EntityScan(basePackages = "pl.ptm.data.model")
@RestController
public class DataStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataStoreApplication.class, args);
    }
}
