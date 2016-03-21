package pl.ptm.data.provider.warsaw.trams;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.ptm.data.provider.DataProvider;
import pl.ptm.data.provider.dto.DataItemDTOBuilder;
import pl.ptm.data.provider.dto.DataSnapshotDTO;
import pl.ptm.data.provider.dto.DataSnapshotDTOBuilder;
import pl.ptm.data.provider.warsaw.trams.dto.WarsawTramDataSnapshotDTO;

import java.util.Date;
import java.util.stream.Collectors;


public class WarsawTramsDataProvider implements DataProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarsawTramsDataProvider.class);

    @Setter
    private String providerId = "warsaw-trams";

    @Setter
    private RestTemplate restTemplate;

    @Setter
    private String warsawUmApiKey;

    @Setter
    private String warsawTramDataWsEndpoint;

    @Override
    public DataSnapshotDTO fetchData() {
        Date date = new Date();
        LOGGER.info("Fetch data, session time : {}", date);

        ResponseEntity<WarsawTramDataSnapshotDTO> forEntity = restTemplate.
                getForEntity(warsawTramDataWsEndpoint + "&apikey=" + warsawUmApiKey, WarsawTramDataSnapshotDTO.class);


        return DataSnapshotDTOBuilder.aDataSnapshotDTO()
                .withDate(date)
                .withProviderId(providerId)
                .withItems(forEntity.getBody().getResult().stream().map(warsawTramDataItemDTO -> DataItemDTOBuilder.aDataItemDTO()
                        .withBrigade(Integer.valueOf(warsawTramDataItemDTO.getBrigade().trim()))
                        .withLat(warsawTramDataItemDTO.getLat())
                        .withLon(warsawTramDataItemDTO.getLon())
                        .withDate(warsawTramDataItemDTO.getTime())
                        .withLine(Integer.valueOf(warsawTramDataItemDTO.getFirstLine().trim()))
                        .withFirstLine(Integer.valueOf(warsawTramDataItemDTO.getFirstLine().trim()))
                        .withStatus(warsawTramDataItemDTO.getStatus())
                        .build()).collect(Collectors.toList()))
                .build();
    }
}
