package com.example.ecommerce.ecommerce.Repository;

import com.example.ecommerce.ecommerce.DAO.Sku;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkuRepository extends JpaRepository<Sku, Long> {
    @Query("SELECT s FROM Sku s JOIN FETCH attributeProductList p WHERE s.product.id = :productId and p.sku.id IS NOT null")
    List<Sku> findByProductId(Long productId);
}
