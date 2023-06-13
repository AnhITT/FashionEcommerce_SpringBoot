package DANHHT.Fashion.service;

import DANHHT.Fashion.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getProductByCategory(Long id);
    Page<Product> findPaginated(int pageNo, int pageSize);
    List<Product> searchProduct(String keyword);
    Optional<Product> getProductById(Long id);
    void UpdateView(Long id);
}
