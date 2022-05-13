package kz.daracademy.repository;

import kz.daracademy.model.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity getCategoryEntitiesByCategoryId (String categoryId);

}
