package pl.ptm.data.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import pl.ptm.data.provider.dto.DataSnapshotDTO;
import pl.ptm.data.service.VehicleDataEventPublisher;
import pl.ptm.data.service.events.NewDataSnapshotEvent;


public class VehicleDataEventPublisherImpl implements VehicleDataEventPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleDataEventPublisherImpl.class);

    private ApplicationEventPublisher applicationEventPublisher;

    public VehicleDataEventPublisherImpl(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publishEvent(DataSnapshotDTO dataSnapshot) {
        LOGGER.debug("Publishing event with new data snapshot : {} : {}",
                dataSnapshot.getProviderId(),
                dataSnapshot.getDate());

        applicationEventPublisher.publishEvent(new NewDataSnapshotEvent(dataSnapshot));

        LOGGER.debug("Event with new data snapshot : {} : {} published",
                dataSnapshot.getProviderId(),
                dataSnapshot.getDate());
    }
}
