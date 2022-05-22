package kz.daracademy.repository;

import kz.daracademy.model.favorite.FavoriteEntity;
import kz.daracademy.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {
    FavoriteEntity getFavoriteEntityByUserIdAndEventId(String userId, String eventId);

    List<FavoriteEntity> getFavoriteEntitiesBy();

    List<FavoriteEntity> getFavoriteEntitiesByUserId(String userId);

    @Transactional
    void deleteFavoriteEntitiesByFavoriteId(String favoriteId);
}
