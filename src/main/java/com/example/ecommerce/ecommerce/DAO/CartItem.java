package com.example.ecommerce.ecommerce.DAO;

import jakarta.persistence.*;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer price;
    Integer qty;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartId")
    Cart cart;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skuId")
    Sku sku;
}
