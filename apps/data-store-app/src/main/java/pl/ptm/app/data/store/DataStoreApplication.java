package pl.ptm.app.data.store;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ptm.data.dao.jpa.DataDaoJpaImpl;
import pl.ptm.data.dao.jpa.DataItemDaoJpaImpl;
import pl.ptm.data.model.DataItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication(scanBasePackages = "pl.ptm")
@EnableScheduling
@EnableJpaRepositories(basePackages = "pl.ptm.data.dao.jpa")
@EntityScan(basePackages = "pl.ptm.data.model")
@RestController
public class DataStoreApplication {

    @Autowired
    private DataItemDaoJpaImpl dataItemDaoJpa;

    @Autowired
    private DataDaoJpaImpl dataDaoJpa;

    public static void main(String[] args) {
        SpringApplication.run(DataStoreApplication.class, args);
    }

    @RequestMapping(path = "/route", method = RequestMethod.GET)
    public List<Point> getPoints(@RequestParam("line") int line,
                                 @RequestParam("brigade") int brigade) {
        return dataItemDaoJpa.findByLineAndBrigade(line, brigade).stream().map(dataItemEntity -> {
            Point point = new Point();
            point.setLine(dataItemEntity.getLine());
            point.setLng(dataItemEntity.getLon());
            point.setLat(dataItemEntity.getLat());
            return point;
        }).collect(Collectors.toList());
    }

    @RequestMapping(path = "/lines", method = RequestMethod.GET)
    public List<Point> getLines() {
        List<Point> list = new ArrayList<>();
        Long maxId = dataDaoJpa.getMaxId();
        for (DataItemEntity dataItemEntity : dataItemDaoJpa.findByDataSnapshotIdentity(
                maxId)) {
            Point point = new Point();
            point.setLine(dataItemEntity.getLine());
            point.setLng(dataItemEntity.getLon());
            point.setLat(dataItemEntity.getLat());
            list.add(point);
        }
        return list;

    }


    public static class Point {
        private int line;
        private double lat;
        private double lng;

        public int getLine() {
            return line;
        }

        public void setLine(int line) {
            this.line = line;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }


}
