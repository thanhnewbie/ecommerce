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
        @NamedAttributeNode("thumbnail")
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

    @OneToMany(mappedBy = "sku", fetch = FetchType.LAZY)
    List<CartItem> cartItemList;
    @OneToMany(mappedBy = "sku", fetch = FetchType.LAZY)
    List<OrderItem> orderItemList;
}
