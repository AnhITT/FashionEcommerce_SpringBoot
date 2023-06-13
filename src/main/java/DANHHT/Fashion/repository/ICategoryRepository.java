package DANHHT.Fashion.repository;

import DANHHT.Fashion.model.Category;
import DANHHT.Fashion.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM  Category c WHERE c.name = :name")
    Optional<Category> findByName(String name);
}
