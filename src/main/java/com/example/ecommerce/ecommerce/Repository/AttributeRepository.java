package com.example.ecommerce.ecommerce.Repository;

import com.example.ecommerce.ecommerce.DAO.Attribute;
import com.example.ecommerce.ecommerce.DAO.AttributeGroup;
import com.example.ecommerce.ecommerce.DAO.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
    public List<Attribute> findAll();

//    @EntityGraph(value = "AttributeDisplayFirst")
//    @Query("select a From Attribute a join a.attributeProduct p where a.isDisplay = 1 and p.productId = :id")
//    public List<Attribute> findAttributeByIsDisplay(Long id);

    @Query("select a From Attribute a join a.attributeProduct p where a.isAttributeSku = 1")
    public List<Attribute> getAttributeByCateId(Long cateID);

}
