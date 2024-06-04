package com.example.ecommerce.ecommerce.DAO;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    User user;

    @OneToMany(mappedBy = "cartItem",fetch = FetchType.LAZY)
    List<CartItem> cartItemList;
}
