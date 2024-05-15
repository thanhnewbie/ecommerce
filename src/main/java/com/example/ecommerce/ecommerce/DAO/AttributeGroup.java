package com.example.ecommerce.ecommerce.DAO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;

//@NamedEntityGraph(name = "AttributeProductList", attributeNodes = {
//        @NamedAttributeNode("id"),
//        @NamedAttributeNode("name"),
//        @NamedAttributeNode(value = "attributeProductList",  subgraph = "attributeProductListGraph")},
//        subgraphs = {
//        @NamedSubgraph(name = "attributeProductListGraph", attributeNodes = {
//                @NamedAttributeNode("attributeName"),
//                @NamedAttributeNode("value")
//        })
//})
@NamedEntityGraph(name = "AttributeList", attributeNodes = {
        @NamedAttributeNode("id"),
        @NamedAttributeNode("name"),
        @NamedAttributeNode(value = "attributeList",  subgraph = "attributeListGraph")},
        subgraphs = {
                @NamedSubgraph(name = "attributeListGraph", attributeNodes = {
                        @NamedAttributeNode("id"),
                        @NamedAttributeNode("name"),
                })
        })


@NamedEntityGraph(name = "AddAttributeList", attributeNodes = {
        @NamedAttributeNode("id"),
        @NamedAttributeNode("name"),
        @NamedAttributeNode(value = "attributeList",  subgraph = "attributeListGraph"),
        @NamedAttributeNode(value = "category",  subgraph = "categoryGraph")},
        subgraphs = {
                @NamedSubgraph(name = "attributeList", attributeNodes = {
                        @NamedAttributeNode("id")
                }),
                @NamedSubgraph(name = "attributeList", attributeNodes = {
                        @NamedAttributeNode("name")
                }),
                @NamedSubgraph(name = "categoryGraph", attributeNodes = {
                        @NamedAttributeNode("id")
                }),

                @NamedSubgraph(name = "attributeList", attributeNodes = {
                        @NamedAttributeNode("isDisplay")
                })
        })
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attribute_group")
public class AttributeGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    Category category;

    @OneToMany(mappedBy = "attributeGroup", cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.LAZY)
    List<Attribute> attributeList;

    @OneToMany(mappedBy = "attributeGroup", cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.LAZY)
    List<AttributeProduct> attributeProductList;

}
