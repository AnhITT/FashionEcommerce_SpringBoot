package DANHHT.Fashion.service;

import DANHHT.Fashion.model.Category;
import DANHHT.Fashion.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getProductByCategory(Long id);
    Product GetProductById(Long id);
    Page<Product> findPaginated(int pageNo, int pageSize);
    List<Product> searchProduct(String keyword);
    Optional<Product> getProductById(Long id);
    void UpdateView(Long id);
    List<Product> top10Product();
    Product top1Product();
    void deleteProdcutById(long id);
    void saveProduct(Product product);
    void updateProduct(Product product);
}
