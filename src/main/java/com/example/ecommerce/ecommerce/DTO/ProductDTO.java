package com.example.ecommerce.ecommerce.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {
    @JsonIgnore
    Long id;
    String name;
    String guaranteeInfo;
    String productInfo;
    String promotionInfo;
    List<GalleryDTO> galeryList;
    List<AttributeProductGroupDTO> attributeProductGroupDTOList;
    List<SkuDTO> skuDTOList;

    List<AttributeProductDTO> attributeDisplayFistList;
}
