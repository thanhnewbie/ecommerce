package com.example.ecommerce.ecommerce.Repository;

import com.example.ecommerce.ecommerce.DAO.AttributeProduct;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeProductRepository extends JpaRepository<AttributeProduct, Long> {
    @EntityGraph(value = "AttributeDisplayFirst")
    @Query("select p from AttributeProduct p join p.attribute a where p.productId =:id and a.isDisplay = 1")
    public List<AttributeProduct> findAttributeByIsDisplay(Long id);
}
