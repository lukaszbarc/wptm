package pl.ptm.data.parser;

import pl.ptm.data.model.VehicleStopEntity;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by jbogacz on 24.03.2016.
 */
public class ZtmDataParser implements DataParser<VehicleStopEntity> {

    private ParsingStrategy parsingStrategy;

    private boolean properBlock = false;

    public ZtmDataParser(){
        this.parsingStrategy = new ZtmParsingStrategy();
    }

    @Override
    public List<VehicleStopEntity> parse(Stream<String> stream) {
        for(String line : (Iterable<String>) stream::iterator) {
            if(line.contains("*ZP")){
                properBlock = true;
                continue;
            }
            else if(line.contains("#ZP")){
                break;
            }

            if(properBlock){
                parsingStrategy.doParse(line);
            }
        }
        return null;
    }
}
