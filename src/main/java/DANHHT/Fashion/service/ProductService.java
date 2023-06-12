package DANHHT.Fashion.service;

import DANHHT.Fashion.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Page<Product> findPaginated(int pageNo, int pageSize);
}
