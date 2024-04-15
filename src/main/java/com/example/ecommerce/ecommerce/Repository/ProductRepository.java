package com.example.ecommerce.ecommerce.Repository;

import com.example.ecommerce.ecommerce.DAO.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
    @EntityGraph(value = "productDetail")
    Optional<Product> findById(Long id);

    @EntityGraph(value = "productOverview")
    List<Product> findAll();

}
