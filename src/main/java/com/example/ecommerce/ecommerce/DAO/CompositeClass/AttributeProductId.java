package com.example.ecommerce.ecommerce.DAO.CompositeClass;

import com.example.ecommerce.ecommerce.DAO.Attribute;
import com.example.ecommerce.ecommerce.DAO.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AttributeProductId implements Serializable {
    @Column(name ="attribute_id")
    Long attributeId;

    @Column(name ="product_id")
    Long productId;

}
