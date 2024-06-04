package com.example.ecommerce.ecommerce.DAO;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String address;
    String fullname;
    String sdt;
    String status;
    Long price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    User user;
    @OneToMany(mappedBy = "orderItem", fetch = FetchType.LAZY)
    List<OrderItem> orderItemList;
}
