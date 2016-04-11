package pl.ptm.data.service.strategy;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by JBOGACZ on 2016-04-06.
 */
@Component
public class FileDataStrategy implements DataStrategy<File> {

    private final String path;

    public FileDataStrategy() {
        this.path = null;
    }

    public FileDataStrategy(String path) {
        this.path = path;
    }

    @Override
    public File[] listFiles() {
        File folder = new File(path);
        File[] files = folder.listFiles();
        return files;
    }

    @Override
    public File lastFile() {
        File lastFile = null;
        for (File file : listFiles()) {
            if(lastFile == null || file.lastModified() > lastFile.lastModified()){
                lastFile = file;
            }
        }
        return lastFile;
    }

    @Override
    public String downloadFile(File file, String targetDir) {
        String downloadedFilePath = targetDir + "/" + file.getName();

        createTargetDir(targetDir);
        try {
            Path path = Paths.get(new File(this.path + "/" + file.getName()).getAbsolutePath());
            FileOutputStream fileOutputStream = new FileOutputStream(downloadedFilePath);
            Files.copy(path, fileOutputStream);
            return downloadedFilePath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTargetDir(String targetDir) {
        File file = new File(targetDir);
        if(!file.exists()){
            file.mkdir();
        }
    }
}
