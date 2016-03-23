package pl.ptm.data.service.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import pl.ptm.data.provider.dto.DataSnapshotDTO;


public class NewDataSnapshotEvent extends ApplicationEvent {

    @Getter
    private DataSnapshotDTO dataSnapshotDTO;

    public NewDataSnapshotEvent(DataSnapshotDTO dataSnapshotDTO) {
        super(dataSnapshotDTO);
        this.dataSnapshotDTO = dataSnapshotDTO;
    }
}
