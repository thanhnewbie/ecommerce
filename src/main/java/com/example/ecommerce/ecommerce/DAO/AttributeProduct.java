package com.example.ecommerce.ecommerce.DAO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Entity
@Table(name = "attribute_product")
//@AssociationOverrides({
//        @AssociationOverride(name = "id.attribute",
//                joinColumns = @JoinColumn(name = "attribute_id")),
//        @AssociationOverride(name = "id.product",
//                joinColumns = @JoinColumn(name = "product_id")) })
public class AttributeProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "product_name")
    String productName;

    @Column(name = "attribute_name")
    String attributeName;

    String value;

    @ManyToOne()
    @JoinColumn(name = "attributeId")
    Attribute attribute;

    @ManyToOne()
    @JoinColumn(name = "productId")
    Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attributeGroupId")
    AttributeGroup attributeGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skuId")
    Sku sku;

}
