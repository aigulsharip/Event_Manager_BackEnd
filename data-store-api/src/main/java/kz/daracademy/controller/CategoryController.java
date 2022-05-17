package kz.daracademy.controller;

import kz.daracademy.model.category.CategoryRequest;
import kz.daracademy.model.category.CategoryResponse;
import kz.daracademy.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }

    @PutMapping
    public CategoryResponse updateCategory(@RequestBody CategoryRequest categoryRequest, @RequestParam String categoryId) {
        categoryRequest.setCategoryId(categoryId);

        return categoryService.updateCategory(categoryRequest);
    }

    @GetMapping("/all")
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategory();
    }

    @GetMapping()
    public CategoryResponse getCategoryById(@RequestParam String categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @DeleteMapping
    public void deleteCategory(@RequestParam String categoryId) {
        categoryService.deleteCategoryByCategoryId(categoryId);
    }


}
