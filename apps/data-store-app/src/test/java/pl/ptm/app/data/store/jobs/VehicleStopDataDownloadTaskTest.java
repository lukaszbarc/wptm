package pl.ptm.app.data.store.jobs;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.ptm.app.data.store.DataStoreApplication;

/**
 * Created by JBOGACZ on 2016-04-07.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DataStoreApplication.class)
@IntegrationTest
public class VehicleStopDataDownloadTaskTest {

    @Autowired
    private VehicleStopDataDownloadTask vehicleStopDataDownloadTask;

    @Test
    @Ignore
    public void testSynchronize() throws Exception {
        vehicleStopDataDownloadTask.synchronize();
    }
}