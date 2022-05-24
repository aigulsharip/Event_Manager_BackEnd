package kz.daracademy.repository;

import kz.daracademy.model.img.ImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ImgRepository extends JpaRepository<ImgEntity, Long> {

//    ImgEntity getImgEntityByImgId(String imgId);

    @Transactional
    List<ImgEntity> getImgEntitiesByEventId(String eventId);

    List<ImgEntity> getImgEntitiesBy();

    @Transactional
    void deleteImgEntityByEventId(String eventId);

}
