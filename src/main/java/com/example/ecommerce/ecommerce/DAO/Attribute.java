package com.example.ecommerce.ecommerce.DAO;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import java.util.List;


//@NamedEntityGraph(name = "AttributeDisplayFirst", attributeNodes = {
//        @NamedAttributeNode("name"),
//        @NamedAttributeNode(value = "attributeProduct",  subgraph = "attributeProductGraph")},
//        subgraphs = {
//                @NamedSubgraph(name = "attributeProductGraph", attributeNodes = {
//                        @NamedAttributeNode("value")
//                })
//        })
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attributes")
@NaturalIdCache
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
//    Integer is_attribute_sku;
//    Integer is_display;
//    Integer attribute_group_id;
    @Column(name = "is_attribute_sku")
    Integer isAttributeSku;

    @Column(name = "is_display")
    Integer isDisplay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attributeGroupId")
    AttributeGroup attributeGroup;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.attribute", cascade = {CascadeType.MERGE, CascadeType.PERSIST})// mappedby là tên của đối tượng này ở phía many
    List<AttributeProduct> attributeProduct;

}
