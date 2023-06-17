package DANHHT.Fashion.repository;

import DANHHT.Fashion.model.Category;
import DANHHT.Fashion.model.Invoice;
import DANHHT.Fashion.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p ORDER BY p.viewProduct DESC LIMIT 1")
    Product top1Products();
    @Query("SELECT p FROM Product p ORDER BY p.viewProduct DESC LIMIT 10")
    List<Product> top10Products();
    @Query("SELECT p FROM Product  p WHERE p.category.id = :id")
    List<Product> findByCategoryId(Long id);

    @Query("""
        SELECT b FROM Product b
        WHERE LOWER(b.nameProduct) LIKE %?1%
        OR LOWER(b.category.name) LIKE %?1%
        """)
    List<Product> searchProduct(String keyword);

    @Query("SELECT p FROM Product p where  p.idProduct =:id")
    Product getProductById(Long id);
}
