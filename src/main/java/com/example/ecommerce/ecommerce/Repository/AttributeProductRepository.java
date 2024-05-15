package com.example.ecommerce.ecommerce.Repository;

import com.example.ecommerce.ecommerce.DAO.AttributeProduct;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeProductRepository extends JpaRepository<AttributeProduct, Long> {
    //@EntityGraph(value = "AttributeDisplayFirst")
    @Query("select p from AttributeProduct p join p.attribute a where p.id =:id and a.isDisplay = 1")
    List<AttributeProduct> findAttributeByIsDisplay(Long id);

    //Tìm id dựa trên productId và attributeId để xác thực entity
    @Query("SELECT ap.id FROM AttributeProduct ap JOIN ap.attribute a JOIN ap.product p WHERE p.id =:productId and a.id = :attributeId")
    Long findAttributeProductId(Long productId, Long attributeId);
}
