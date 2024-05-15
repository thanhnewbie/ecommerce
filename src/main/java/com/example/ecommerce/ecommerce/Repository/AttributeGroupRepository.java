package com.example.ecommerce.ecommerce.Repository;

import com.example.ecommerce.ecommerce.DAO.AttributeGroup;
import com.example.ecommerce.ecommerce.DTO.AttributeGroupDTO;
import net.minidev.json.JSONUtil;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeGroupRepository extends JpaRepository<AttributeGroup, Long> {

//    @EntityGraph(value = "AttributeProductList")
//    @Query("select g From AttributeGroup g join g.attributeProductList p where p.id = :id")
//    List<AttributeGroup> findAttributeByProductId(Long id);

//    @EntityGraph(value = "AddAttributeProductList")
//    @Query("select g From AttributeGroup g join fetch g.attributeList l WHERE g.category.id = :id")
//    List<AttributeGroup> findAttributeGroupByCateId(Long id);

    @EntityGraph(value = "AttributeList")
    @Query("select g From AttributeGroup g join fetch g.attributeList l WHERE g.category.id = :id")
    List<AttributeGroup> findAttributeGroupByCateId(Long id);


    @Query("select g From AttributeGroup g join fetch attributeProductList a WHERE a.product.id = :productId")
    List<AttributeGroup> findAttributeGroupByProductId(Long productId);

//    @EntityGraph(value = "AddAttributeProductList")
//    @Query("select g From AttributeGroup g join fetch g.attributeList l join fetch g.category c where c.id = :id")
//    List<AttributeGroup> findAttributeGroupByCateId(Long id);




}
