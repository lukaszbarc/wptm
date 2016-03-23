package pl.ptm.data.provider;


import pl.ptm.data.provider.dto.DataSnapshotDTO;

public interface DataProvider {

    DataSnapshotDTO fetchData();

    String name();
}
