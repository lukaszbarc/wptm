package pl.ptm.data.provider;

import pl.ptm.data.provider.dto.DataSnapshotDTO;

/**
 * Created by jbogacz on 23.03.2016.
 */
public class StopsDataProvider implements DataProvider {

    private static final String GOOGLE_KEY = "AIzaSyBTtFaE3jJxgFEBFhg10X9a45Xhb6CxsUA";

    @Override
    public DataSnapshotDTO fetchData() {
        return null;
    }

    @Override
    public String name() {
        return "warsaw-trams-stops";
    }

}
