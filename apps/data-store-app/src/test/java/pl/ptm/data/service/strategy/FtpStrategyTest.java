package pl.ptm.data.service.strategy;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by JBOGACZ on 2016-04-08.
 */
public class FtpStrategyTest {

    @Ignore
    public void testListFiles() throws Exception {

        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("rozklady.ztm.waw.pl", 21);
        ftpClient.login("anonymous", "");
        FTPFile[] ftpFiles = ftpClient.listFiles();

        int length = ftpFiles.length;

    }
}