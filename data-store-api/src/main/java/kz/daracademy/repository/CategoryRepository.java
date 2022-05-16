package kz.daracademy.repository;

import kz.daracademy.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getCategoryEntitiesByCategoryId (String categoryId);

}
