package com.example.ecommerce.ecommerce.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    @JsonIgnore
    Long id;
    String name;
    String guaranteeInfo;
    String productInfo;
    String promotionInfo;
    List<ProductImageDTO> productImageDTOList;
    //List<AttributeProductGroupDTO> attributeProductGroupDTOList;
    List<SkuDTO> skuDTOList;
    List<AttributeProductDTO> attributeDisplayFistList;
}
