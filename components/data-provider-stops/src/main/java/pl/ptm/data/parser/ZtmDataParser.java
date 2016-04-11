package pl.ptm.data.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.ptm.data.model.VehicleStopEntity;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by jbogacz on 24.03.2016.
 */
@Component
public class ZtmDataParser implements DataParser<VehicleStopEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZtmDataParser.class);

    private ParsingStrategy parsingStrategy;

    private boolean properBlock = false;

    public ZtmDataParser(){
        this.parsingStrategy = new ZtmParsingStrategy();
    }

    @Override
    public List<VehicleStopEntity> parse(Stream<String> stream) {
        parseLineByLine(stream);
        removeStopsWithoutTrams();
        return parsingStrategy.getData();
    }

    private void parseLineByLine(Stream<String> stream) {
        String debbugLine = "";
        int i = 0;
        try {
            for (String line : (Iterable<String>) stream::iterator) {
                debbugLine = line;
                if (line.contains("*ZP")) {
                    properBlock = true;
                    continue;
                } else if (line.contains("#ZP")) {
                    break;
                }
                if (properBlock) {
                    parsingStrategy.doParse(line);
                }
                i++;
            }
        } catch (Exception e){
            LOGGER.error("Error in line " + (i + 2) + ":" + debbugLine, e);
            throw new RuntimeException(e);
        }
    }

    private void removeStopsWithoutTrams() {
        parsingStrategy.doCleaning();
    }
}
