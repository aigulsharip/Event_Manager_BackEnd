package kz.daracademy.repository;

import kz.daracademy.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity getUserEntityByUserId(String userId);

    List<UserEntity> getUserEntitiesBy();

    @Transactional
    void deleteUserEntityByUserId(String userId);


}
