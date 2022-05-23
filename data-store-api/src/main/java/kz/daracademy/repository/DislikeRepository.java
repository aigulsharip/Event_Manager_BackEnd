package kz.daracademy.repository;

import kz.daracademy.model.dislike.DislikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface DislikeRepository extends JpaRepository<DislikeEntity, Long> {
    List<DislikeEntity> getDislikeEntityByEventId(String eventId);

    DislikeEntity getDislikeEntityByEventIdAndUserId(String eventId, String userId);

    @Transactional
    void deleteDislikeEntityByEventIdAndUserId(String eventId, String userId);
}
