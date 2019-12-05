package coffee.inventory.service;

import java.util.Collection;

import coffee.inventory.entity.Category;

public interface CategoryService {
    Collection<Category> findAllCategories();
}