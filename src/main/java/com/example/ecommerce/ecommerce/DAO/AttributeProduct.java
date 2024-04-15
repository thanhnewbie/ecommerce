package com.example.ecommerce.ecommerce.DAO;

import com.example.ecommerce.ecommerce.DAO.CompositeClass.AttributeProductId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NamedEntityGraph(name = "AttributeDisplayFirst", attributeNodes = {
        @NamedAttributeNode("value"),
        @NamedAttributeNode(value = "attribute",  subgraph = "attributeGraph")},
        subgraphs = {
        @NamedSubgraph(name = "attributeGraph", attributeNodes = {
                @NamedAttributeNode("name")
        })
})
@Data
@AllArgsConstructor
@NoArgsConstructor

//@IdClass(AttributeProductId.class)
@Entity
@Table(name = "attribute_products")
@AssociationOverrides({
        @AssociationOverride(name = "id.attribute",
                joinColumns = @JoinColumn(name = "attribute_id")),
        @AssociationOverride(name = "id.product",
                joinColumns = @JoinColumn(name = "product_id")) })
public class AttributeProduct {


    public Long id;

    @Transient
    @Column(name = "product_name")
    String productName;
    @Transient
    @Column(name = "attribute_name")
    String attributeName;
    @Transient
    String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id")
    //@JoinColumn(name = "attributeId"/*,insertable = false, updatable = false*/)
    Attribute attribute;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    //@JoinColumn(name = "productId"/*,insertable = false, updatable = false*/)
    Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attributeGroupId")
    AttributeGroup attributeGroup;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_id")
    Sku sku;

}
