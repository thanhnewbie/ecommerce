package com.example.ecommerce.ecommerce.DTO;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonFilter("AttributeDTOFilter")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttributeProductDTO {
    Long productId;
    Long attributeId;
    String name;
    String value;
}

// thêm sản phẩm
// thêm attribute  attributeDTO giống attribute entity
// hiển thị chi tiết sản phẩm =>
    //List<AttributeGroup>: name list<Attribute>
    //List<Attribute> => sku
    // list<gallery>
    // list<attribute> hiển thị thuộc tính chung
// hiển thị tổng quan sản phẩm  => product
