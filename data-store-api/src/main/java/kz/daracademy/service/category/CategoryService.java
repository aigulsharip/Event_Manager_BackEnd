package kz.daracademy.service.category;

import kz.daracademy.model.category.CategoryRequest;
import kz.daracademy.model.category.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);

    CategoryResponse updateCategory(CategoryRequest categoryRequest);

    List<CategoryResponse> getAllCategory();

    CategoryResponse getCategoryById(String categoryId);

    void deleteCategoryByCategoryId(String categoryId);


}
