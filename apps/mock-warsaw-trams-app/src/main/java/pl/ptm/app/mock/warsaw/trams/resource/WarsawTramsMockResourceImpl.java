package pl.ptm.app.mock.warsaw.trams.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.ptm.app.mock.warsaw.trams.service.WarsawTramsMockService;
import pl.ptm.data.provider.warsaw.trams.dto.WarsawTramDataSnapshotDTO;

@RestController
@RequestMapping(value = "/trams", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WarsawTramsMockResourceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarsawTramsMockResourceImpl.class);
    private static int count = 0;
    @Autowired
    private WarsawTramsMockService warsawTramsMockService;

    @RequestMapping(method = RequestMethod.GET)
    public WarsawTramDataSnapshotDTO providePreparedData() {
        LOGGER.info("providePreparedData()");
        return warsawTramsMockService.provideNextPreparedData();
    }

    @RequestMapping(value = "/withPossibleEmptyResult", method = RequestMethod.GET)
    public WarsawTramDataSnapshotDTO providePreparedDataWithFailurePossible(@RequestParam("emptyResultEveryN") int emptyResultEveryN) {
        LOGGER.info("providePreparedDataWithFailurePossible()");
        if (count++ % emptyResultEveryN == 0) {
            LOGGER.info("return empty result");
            return new WarsawTramDataSnapshotDTO();
        }
        return providePreparedData();
    }

}
