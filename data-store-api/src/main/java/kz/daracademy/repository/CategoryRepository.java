package kz.daracademy.repository;

import kz.daracademy.model.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity getCategoryEntityByCategoryId(String categoryId);

    List<CategoryEntity> getCategoryEntitiesBy();

    void deleteCategoryEntityByCategoryId(String categoryId);
}
