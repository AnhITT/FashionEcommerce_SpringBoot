package DANHHT.Fashion.service;

import DANHHT.Fashion.model.Category;
import DANHHT.Fashion.model.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> getCategoryByName(String name);
    List<Category> getAllCategory();
    void saveCategory(Category category);
    Category getCategoryById(long id);
    void deleteCategoryById(long id);
}
