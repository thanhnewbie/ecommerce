package com.example.ecommerce.ecommerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkuDTO {
    Long id;
    String thumbnail;
    Long price;
    Integer available;
    List<AttributeProductDTO> attributeDTOList;

}
