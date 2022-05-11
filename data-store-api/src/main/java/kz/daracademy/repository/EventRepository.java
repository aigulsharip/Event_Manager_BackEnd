package kz.daracademy.repository;

import kz.daracademy.model.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    EventEntity getEventEntityByEventId (String eventId);

    List<EventEntity> getEventEntitiesBy();

    @Transactional
    void deleteEventEntityByEventId(String eventId);

    List<EventEntity> findEventEntitiesByCategory_CategoryId(String category_id);

    List<EventEntity> findEventEntitiesByCategory_Name(String categoryName);

}
