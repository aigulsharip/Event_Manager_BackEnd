package kz.daracademy.controller;

import kz.daracademy.feign.DataStoreFeign;
import kz.daracademy.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private DataStoreFeign dataStoreFeign;

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return dataStoreFeign.createCategory(category);
    }

    @PutMapping
    public Category updateCategory(@RequestBody Category category, @RequestParam String categoryId) {
        category.setCategoryId(categoryId);

        return dataStoreFeign.updateCategory(category, categoryId);
    }

    @GetMapping("/all")
    public List<Category> getAllCategories() {
        return dataStoreFeign.getAllCategorories();
    }

    @GetMapping()
    public Category getCategoryById(@RequestParam String categoryId) {
        return  dataStoreFeign.getCategoryById(categoryId);
    }

    @DeleteMapping
    public void deleteCategory(@RequestParam String categoryId) {
        dataStoreFeign.deleteCategory(categoryId);
    }
}
