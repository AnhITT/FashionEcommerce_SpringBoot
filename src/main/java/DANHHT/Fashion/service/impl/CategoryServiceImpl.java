package DANHHT.Fashion.service.impl;

import DANHHT.Fashion.model.Category;
import DANHHT.Fashion.model.Product;
import DANHHT.Fashion.repository.ICategoryRepository;
import DANHHT.Fashion.repository.IProductRepository;
import DANHHT.Fashion.service.CategoryService;
import DANHHT.Fashion.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
    @Override
    public List<Category> getAllCategory()
    {
        return categoryRepository.findAll();
    }
    @Override
    public void saveCategory(Category category){
        this.categoryRepository.save(category);
    }
    @Override
    public Category getCategoryById(long id){
        Optional<Category> optional = categoryRepository.findById(id);
        if(optional.isPresent())
        {
            return optional.get();
        } else {
            throw new RuntimeException("Category not found for id " + id);
        }
    }

    @Override
    public void deleteCategoryById(long id){
        this.categoryRepository.deleteById(id);
    }
}
