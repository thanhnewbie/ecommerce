package com.example.ecommerce.ecommerce.DAO;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "category_name")
    String name;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.LAZY)
    List<Product> productList;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.LAZY )
    List<Attribute> attributeList;

    @OneToMany(mappedBy = "category",cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.LAZY)
    List<AttributeGroup> attribute_groupList;

    public Category(Long l, String laptop) {
        this.id = l;
        this.name = laptop;
    }
}
