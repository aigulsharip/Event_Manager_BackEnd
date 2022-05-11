package kz.daracademy.repository;

import kz.daracademy.model.EventEntity;
import kz.daracademy.model.EventResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    EventEntity getEventEntityByEventId (String eventId);

    List<EventEntity> getEventEntitiesBy();

    @Transactional
    void deleteEventEntityByEventId(String eventId);

    List<EventEntity> findEventEntitiesByCategory_CategoryId(String category_id);

    List<EventEntity> findEventEntitiesByCategory_Name(String categoryName);

    List<EventEntity> findEventEntitiesByVotesGreaterThan(Integer votes);

    List<EventEntity> findEventEntitiesByStartDateTimeAfter(Date date);

}
