package com.example.ecommerce.ecommerce.DTO;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonFilter("AttributeDTOFilter")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttributeDTO {
    Long id;
    String name;
    Integer isDisplay;
    Integer isAttributeSku;
    public AttributeDTO(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
