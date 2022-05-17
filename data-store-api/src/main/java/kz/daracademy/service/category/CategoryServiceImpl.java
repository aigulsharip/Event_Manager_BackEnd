package kz.daracademy.service.category;

import kz.daracademy.model.category.CategoryEntity;
import kz.daracademy.model.category.CategoryRequest;
import kz.daracademy.model.category.CategoryResponse;
import kz.daracademy.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        CategoryEntity categoryEntity = modelMapper.map(categoryRequest, CategoryEntity.class);
        categoryEntity = categoryRepository.save(categoryEntity);
        return modelMapper.map(categoryEntity, CategoryResponse.class);

    }

    @Override
    public CategoryResponse updateCategory(CategoryRequest categoryRequest) {
        CategoryEntity categoryEntity = modelMapper.map(categoryRequest, CategoryEntity.class);
        CategoryEntity dbCatEntity = categoryRepository.getCategoryEntityByCategoryId(categoryRequest.getCategoryId());
        categoryEntity.setId(dbCatEntity.getId());
        categoryEntity = categoryRepository.save(categoryEntity);
        return modelMapper.map(categoryEntity, CategoryResponse.class);


    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        return categoryRepository.getCategoryEntitiesBy().stream().map(category -> modelMapper.map(category, CategoryResponse.class)).collect(Collectors.toList());


    }

    @Override
    public CategoryResponse getCategoryById(String categoryId) {
        CategoryEntity categoryEntity = categoryRepository.getCategoryEntityByCategoryId(categoryId);
        return modelMapper.map(categoryEntity, CategoryResponse.class);
    }

    @Override
    public void deleteCategoryByCategoryId(String categoryId) {
        categoryRepository.deleteCategoryEntityByCategoryId(categoryId);
    }
}
