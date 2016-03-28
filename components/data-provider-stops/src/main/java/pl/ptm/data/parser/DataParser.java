package pl.ptm.data.parser;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by jbogacz on 24.03.2016.
 */
public interface DataParser<E> {

    List<E> parse(Stream<String> stream);

}
