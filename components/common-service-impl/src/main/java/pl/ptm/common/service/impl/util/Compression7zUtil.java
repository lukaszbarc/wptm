package pl.ptm.common.service.impl.util;

import lombok.Data;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by JBOGACZ on 2016-04-07.
 */
public class Compression7zUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(Compression7zUtil.class);

    public String extract(String filePath){
        return extract(filePath, false);
    }

    public String extract(String filePath, boolean deleteOriginal){
        try {
            return extractWithException(filePath, deleteOriginal);
        } catch (IOException e) {
            LOGGER.error("Error during file extraction: " + filePath, e);
            throw new RuntimeException(e);
        }
    }

    private String extractWithException(String filePath, boolean deleteOriginal) throws IOException {
        File fileToExtract = new File(filePath);
        SevenZFile sevenZFile = new SevenZFile(fileToExtract);
        SevenZArchiveEntry entry = sevenZFile.getNextEntry();
        String extractedFilePath = null;
        while(entry!=null){
            extractedFilePath = fileToExtract.getParent() + "/" + entry.getName();
            LOGGER.info("Extracting file: " + filePath);
            LOGGER.info("To: " + extractedFilePath);

            FileOutputStream out = new FileOutputStream(extractedFilePath);
            byte[] content = new byte[(int) entry.getSize()];
            sevenZFile.read(content, 0, content.length);
            out.write(content);
            out.close();
            entry = sevenZFile.getNextEntry();
        }
        sevenZFile.close();
        if(deleteOriginal) {
            fileToExtract.delete();
        }
        return extractedFilePath;
    }
}
