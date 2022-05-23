package kz.daracademy.repository;

import kz.daracademy.model.like.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    List<LikeEntity> getLikeEntityByEventId(String eventId);

    LikeEntity getLikeEntityByEventIdAndUserId(String eventId, String userId);

    @Transactional
    void deleteLikeEntityByEventIdAndUserId(String eventId, String userId);
}
