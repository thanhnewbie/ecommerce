package com.example.ecommerce.ecommerce.DTO.Custom;

import com.example.ecommerce.ecommerce.DTO.AttributeProductDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttributeProductGroupDTO {
    Long id;
    String name;
    List<AttributeProductDTO> attributeProductDTOList;// hiển thị ở trang chi tiết sản phâm
}
