package pl.ptm.data.service.ztm;

import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.ptm.common.service.impl.util.Compression7zUtil;
import pl.ptm.data.dao.jpa.VehicleStopDaoJpa;
import pl.ptm.data.dao.jpa.ZtmSyncFileDaoJpa;
import pl.ptm.data.model.VehicleStopEntity;
import pl.ptm.data.model.ZtmSyncFileEntity;
import pl.ptm.data.parser.DataParser;
import pl.ptm.data.service.strategy.DataStrategy;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by jbogacz on 2016-04-01.
 */
@Component
public class VehicleStopsSynchronizationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleStopsSynchronizationService.class);

    private final Compression7zUtil compression7zUtil = new Compression7zUtil();

    @Value("${ztm.ftp.download.directory}")
    private String downloadDir;

    @Autowired
    private DataStrategy<FTPFile> dataStrategy;

    @Autowired
    private DataParser<VehicleStopEntity> ztmDataParser;

    @Autowired
    private VehicleStopDaoJpa vehicleStopDao;

    @Autowired
    private ZtmSyncFileDaoJpa ztmSyncFileDao;

    public void synchronizeFtpData() throws URISyntaxException, IOException {
//        parseClearSave("/tmp/downloaded/RA160329.txt");
//        parseClearSave("/tmp/downloaded/RA160415.txt");

        String filePath = downloadNewDataAndGetPath();
        if(filePath != null) {
            String extractedArchive = unZipArchive(filePath);
            parseClearSave(extractedArchive);
        }
    }

    private String unZipArchive(String filePath) {
        boolean deleteOriginal = true;
        return compression7zUtil.extract(filePath, deleteOriginal);
    }

    private void parseClearSave(String filePath) throws IOException, URISyntaxException {
        List<VehicleStopEntity> ztmStops = ztmDataParser.parse(
                createWindows1250Stream(filePath));
        vehicleStopDao.deleteAll();
        vehicleStopDao.save(ztmStops);
    }

    private Stream<String> createWindows1250Stream(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath), Charset.forName("Windows-1250"));
    }

    private String downloadNewDataAndGetPath() {
        FTPFile ftpFile = dataStrategy.lastFile();
        ZtmSyncFileEntity byName = ztmSyncFileDao.findByName(ftpFile.getName());
        if(byName == null){
            LOGGER.info("No checkpoint data for new file - start downloading: " + ftpFile.getName());
            return dataStrategy.downloadFile(ftpFile, downloadDir);
        } else {
            return null;
        }
    }
}
