package pl.ptm.data.parser;

import java.util.List;

/**
 * Created by jbogacz on 25.03.2016.
 */
public interface ParsingStrategy<E> {

    void doParse(String line);

    List<E> getData();

    void doCleaning();
}
