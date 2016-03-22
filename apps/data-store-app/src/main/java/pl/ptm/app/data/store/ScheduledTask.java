package pl.ptm.app.data.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.ptm.data.dao.jpa.DataDaoJpaImpl;
import pl.ptm.data.model.DataItemEntity;
import pl.ptm.data.model.DataSnapshotEntity;
import pl.ptm.data.provider.DataProvider;
import pl.ptm.data.provider.dto.DataSnapshotDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduledTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTask.class);

    @Autowired
    private List<DataProvider> dataProviders;

    @Autowired
    private DataDaoJpaImpl dao;

    @Scheduled(fixedRate = 30000)
    public void test() {
        for (DataProvider dataProvider : dataProviders) {
            DataSnapshotDTO dataSnapshotDTO = dataProvider.fetchData();
            DataSnapshotEntity dataSnapshot = new DataSnapshotEntity();
            dataSnapshot.setDate(dataSnapshotDTO.getDate());
            dataSnapshot.setProviderId(dataSnapshotDTO.getProviderId());
            dataSnapshot.setItems(dataSnapshotDTO.getItems().stream().map(dataItemDTO -> {
                DataItemEntity di = new DataItemEntity();
                di.setDate(dataItemDTO.getDate());
                di.setFirstLine(dataItemDTO.getFirstLine());
                di.setBrigade(dataItemDTO.getBrigade());
                di.setLat(dataItemDTO.getLat());
                di.setLon(dataItemDTO.getLon());
                di.setLine(dataItemDTO.getLine());
                di.setStatus(dataItemDTO.getStatus());
                di.setDataSnapshot(dataSnapshot);
                return di;
            }).collect(Collectors.toList()));

            LOGGER.info("Inserting snapshot with {} items", dataSnapshot.getItems().size());

            DataSnapshotEntity save = dao.save(dataSnapshot);
            LOGGER.info("Snapshot identity : {}", save.getIdentity());
        }

    }
}
