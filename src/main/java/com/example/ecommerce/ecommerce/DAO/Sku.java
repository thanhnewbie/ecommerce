package com.example.ecommerce.ecommerce.DAO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NamedEntityGraph(name = "SkuList", attributeNodes = {
        @NamedAttributeNode("id"),
        @NamedAttributeNode("price"),
        @NamedAttributeNode("pricePromotion"),
        @NamedAttributeNode("thumbnail"),
        @NamedAttributeNode(value = "attributeSkuList",  subgraph = "attributeSkuListGraph")},
        subgraphs = {
                @NamedSubgraph(name = "attributeSkuListGraph", attributeNodes = {
                        @NamedAttributeNode("attributeName"),
                        @NamedAttributeNode("value"),
                })
        })
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "skus")
public class Sku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String value;
    Long price;
    @Column(name = "price_promotion")
    Long pricePromotion;
    Integer available;
    String thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    Product product;

    @OneToMany(mappedBy = "sku",cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.LAZY)
    List<AttributeProduct> attributeProductList;
}
