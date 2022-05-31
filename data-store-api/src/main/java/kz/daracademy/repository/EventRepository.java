package kz.daracademy.repository;

import kz.daracademy.model.event.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    EventEntity getEventEntityByEventId(String eventId);

    List<EventEntity> getEventEntitiesBy();

    @Transactional
    void deleteEventEntityByEventId(String eventId);

    List<EventEntity> findEventEntitiesByCategory_CategoryId(String category_id);

    List<EventEntity> findEventEntitiesByCategory_CategoryName(String categoryName);

//    List<EventEntity> findEventEntitiesByVotesGreaterThanOrderByVotesAsc(Integer votes);
    List<EventEntity> findEventEntitiesByLikesGreaterThanOrderByLikesAsc(Integer likes);

    List<EventEntity> findEventEntitiesByStartDateTimeAfterOrderByStartDateTime(Date date);

    List<EventEntity> findEventEntitiesByPostedDateAfterOrderByPostedDate(Date date);


    List<EventEntity> findEventEntitiesByStartDateTimeBetweenOrderByStartDateTime(Date startDate, Date endDate);


}
