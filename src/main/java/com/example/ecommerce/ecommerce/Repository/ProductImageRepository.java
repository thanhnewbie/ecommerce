package com.example.ecommerce.ecommerce.Repository;

import com.example.ecommerce.ecommerce.DAO.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM product_images where product_id = ?", nativeQuery = true)
    void deleteByProductId(Long productId);
}
