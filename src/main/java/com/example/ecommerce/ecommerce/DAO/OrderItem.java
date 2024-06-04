package com.example.ecommerce.ecommerce.DAO;

import jakarta.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long price;
    Long qty;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skuId")
    Sku sku;
}
