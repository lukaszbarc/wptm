package pl.ptm.app.mock.warsaw.trams.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import pl.ptm.data.provider.warsaw.trams.dto.WarsawTramDataSnapshotDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileBasedWarsawTramsMockServiceImpl implements WarsawTramsMockService, InitializingBean {

    private static int index = 0;
    private List<WarsawTramDataSnapshotDTO> snapshots;
    @Autowired
    private ResourcePatternResolver resourceResolver;

    @Override
    public WarsawTramDataSnapshotDTO provideNextPreparedData() {
        if (index == snapshots.size()) {
            index = 0;
        }
        return snapshots.get(index++);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        snapshots = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Resource resource : resourceResolver.getResources("classpath:/snapshots/*.json")) {
            WarsawTramDataSnapshotDTO campaignDefinitions = mapper.readValue(resource.getURL(),
                    WarsawTramDataSnapshotDTO.class);
            snapshots.add(campaignDefinitions);
        }
    }
}
