package pl.ptm.data.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.ptm.data.dao.jpa.VehicleStopDaoJpa;
import pl.ptm.data.model.VehicleStopEntity;
import pl.ptm.data.parser.DataParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by jbogacz on 2016-04-01.
 */
@Component
public class VehicleStopsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleStopsService.class);

    @Autowired
    private DataParser<VehicleStopEntity> ztmDataParser;

    @Autowired
    private VehicleStopDaoJpa vehicleStopDao;

    public void synchronizeFtpData() throws URISyntaxException, IOException {
        if(checkIfDataToSync()){
            parseClearSave();
        }
    }

    private void parseClearSave() throws IOException, URISyntaxException {
        List<VehicleStopEntity> ztmStops = ztmDataParser.parse(createTestStream());
        vehicleStopDao.deleteAll();
        vehicleStopDao.save(ztmStops);
    }

    private Stream<String> createTestStream() throws IOException, URISyntaxException {
        String resourceName = "RA160329.txt";
        checkResourceExists(resourceName);
        return Files.lines(Paths.get(ClassLoader.getSystemResource(resourceName).toURI()));
    }

    private void checkResourceExists(String resourceName) throws IOException {
        if(ClassLoader.getSystemResource(resourceName) == null){
            throw new IOException(resourceName + " resource doesn't exist");
        }
    }

    private boolean checkIfDataToSync() {
        boolean sync = true;
        if(!sync){
            LOGGER.info("Nothing to synchronize");
        }
        return sync;
    }
}
