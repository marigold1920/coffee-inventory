package coffee.inventory.service.implement;

import java.util.Collection;

import coffee.inventory.entity.Category;
import coffee.inventory.repository.CategoryRepository;
import coffee.inventory.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Collection<Category> findAllCategories() {

        return categoryRepository.findAll();
    }

}