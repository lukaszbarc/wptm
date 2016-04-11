package pl.ptm.data.service.strategy;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by JBOGACZ on 2016-04-08.
 */
@Component
public class FtpStrategy implements DataStrategy<FTPFile>{

    private static final Logger LOGGER = LoggerFactory.getLogger(FtpStrategy.class);

    @Value("${ztm.ftp.port}")
    private String port;

    @Value("${ztm.ftp.url}")
    private String url;

    @Value("${ztm.ftp.user}")
    private String user;

    @Value("${ztm.ftp.password}")
    private String password;

    private FTPClient ftpClient = new FTPClient();

    @Override
    public FTPFile[] listFiles() {
        try {
            connect();
            return ftpClient.listFiles();
        } catch (IOException e) {
            LOGGER.error("Error when trying connect to ftp://" + url + ":" + port, e);
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    @Override
    public FTPFile lastFile() {
        FTPFile[] ftpFiles = listFiles();
        if(ftpFiles.length > 0){
            return ftpFiles[ftpFiles.length - 1];
        } else {
            throw new RuntimeException("No files at ftp://" + url);
        }
    }

    @Override
    public String downloadFile(FTPFile ftpFile, String targetDir) {
        String downloadPath = targetDir + "/" + ftpFile.getName();
        try {
            connect();
            setBinaryFileType();

            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadPath));

            boolean success = ftpClient.retrieveFile(ftpFile.getName(), outputStream);
            if(success){
                LOGGER.info("File has been downloaded successfully");
            }

            outputStream.close();
            return downloadPath;
        } catch (IOException e){
            LOGGER.error("Error when try to download file: " + ftpFile.getName() + " from ftp://" + url);
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    private void setBinaryFileType() throws IOException {
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    }

    private void connect() throws IOException {
        ftpClient.connect(url, Integer.valueOf(port));
        ftpClient.login(user, password);
    }

    private void disconnect() {
        try {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (IOException ex) {
            LOGGER.error("Error when trying disconnect from ftp://" + url + ":" + port, ex);
            throw new RuntimeException(ex);
        }
    }
}
