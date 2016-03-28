package pl.ptm.data.parser;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by jbogacz on 25.03.2016.
 */
public class ZtmDataParserTest {

    public static final String TEST_FILE = "RA160329.txt";

    private ZtmDataParser parser = new ZtmDataParser();

    @Test
    public void testParse() throws Exception {
        parser.parse(
                createStreamForTestFile());
    }

    private Stream<String> createStreamForTestFile() throws IOException, URISyntaxException {
        return Files.lines(Paths.get(ClassLoader.getSystemResource(TEST_FILE)
                .toURI()));
    }
}