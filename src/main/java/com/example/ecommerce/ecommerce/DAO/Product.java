package com.example.ecommerce.ecommerce.DAO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;



@NamedEntityGraph(name = "productOverview", attributeNodes = {
        @NamedAttributeNode("id"),
        @NamedAttributeNode("name"),
        @NamedAttributeNode("priceLowest"),
        @NamedAttributeNode("thumbnail"),
})

@NamedEntityGraph(name = "productDetail", attributeNodes = {
        @NamedAttributeNode("id"),
        @NamedAttributeNode("name"),
        @NamedAttributeNode("guaranteeInfo"),
        @NamedAttributeNode("productInfo"),
        @NamedAttributeNode("promotionInfo"),
        @NamedAttributeNode(value = "galleryList", subgraph = "galleryListGraph"),
        @NamedAttributeNode(value = "attributeProductList", subgraph = "attributeProductListGraph"),
        @NamedAttributeNode(value = "skuList", subgraph =  "skuListGraph"),
},subgraphs = {
        @NamedSubgraph(name = "galleryListGraph", attributeNodes = {
                @NamedAttributeNode("image")
        }),
        @NamedSubgraph(name = "attributeProductListGraph", attributeNodes = {
                @NamedAttributeNode("attributeId"),
                @NamedAttributeNode("attributeName"),
                @NamedAttributeNode("value")
        }),
        @NamedSubgraph(name = "skuListGraph", attributeNodes = {
                @NamedAttributeNode("id"),
                @NamedAttributeNode("price"),
                @NamedAttributeNode("thumbnail"),
                @NamedAttributeNode("available")
        })
} )

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @Column(name = "price_lowest")
    Long priceLowest;

    @Column(name = "guarantee_info")
    String guaranteeInfo;

    @Column(name = "product_info")
    String productInfo;

    @Column(name = "promotion_info")
    String promotionInfo;

    @Column(name = "thumbnail")
    String thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    Category category;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.LAZY)

    List<AttributeProduct> attributeProductList;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.LAZY)

    List<Sku> skuList;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.ALL},fetch = FetchType.LAZY)

    List<Gallery> galleryList;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;
    @Column(name = "created_at")
    LocalDateTime createdAt;
    Boolean deleted;// 0 == false, 1 == true
}
