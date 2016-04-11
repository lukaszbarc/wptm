package pl.ptm.data.service.strategy;

/**
 * Created by JBOGACZ on 2016-04-06.
 */
public interface DataStrategy<T> {

    T[] listFiles();

    T lastFile();

    String downloadFile(T ftpFile, String targetDir);
}
