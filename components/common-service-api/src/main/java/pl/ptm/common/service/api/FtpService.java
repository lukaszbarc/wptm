package pl.ptm.common.service.api;


import org.apache.commons.net.ftp.FTPFile;

/**
 * Created by JBOGACZ on 2016-04-06.
 */
public interface FtpService {

    FTPFile[] listFiles();

    FTPFile lastFile();

    void downloadFile(FTPFile ftpFile, String targetDir);

}
