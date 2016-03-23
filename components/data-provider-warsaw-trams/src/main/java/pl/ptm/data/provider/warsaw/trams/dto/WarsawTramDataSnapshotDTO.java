package pl.ptm.data.provider.warsaw.trams.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WarsawTramDataSnapshotDTO {

    private List<WarsawTramDataItemDTO> result = new ArrayList<>();
}
