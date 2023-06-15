package DANHHT.Fashion.service.impl;

import DANHHT.Fashion.model.Product;
import DANHHT.Fashion.repository.IProductRepository;
import DANHHT.Fashion.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByCategory(Long id) {
        return productRepository.findByCategoryId(id);
    }
    @Override
    public Page<Product> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo -1, pageSize);
        return this.productRepository.findAll(pageable);
    }
    @Override
    public List<Product> searchProduct(String keyword) {
        keyword = keyword.toLowerCase();
        return productRepository.searchProduct(keyword);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    @Override
    public Product GetProductById(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        Product product = null;
        if(optional.isPresent()){
            product = optional.get();
        }
        else {
            throw new RuntimeException("Product not found for id :" + id);
        }
        return product;
    }
    @Override
    public void UpdateView(Long id) {
        var product = productRepository.findById(id);
        if (product.isPresent()) {
            Product productEntity = product.get();
            int currentViewCount = productEntity.getViewProduct();
            productEntity.setViewProduct(currentViewCount + 1);
            productRepository.save(productEntity);
        }
    }

    @Override
    public List<Product> top10Product() {
        return productRepository.top10Products();
    }

    @Override
    public Product top1Product() {
        return productRepository.top1Products();
    }
}
