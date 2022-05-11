package kz.daracademy.repository;

import kz.daracademy.model.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

    EventEntity getEventEntityByEventId (String eventId);

    List<EventEntity> getEventEntitiesBy();

    @Transactional
    void deleteEventEntityByEventId(String eventId);


}
