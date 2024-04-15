package com.example.ecommerce.ecommerce.DTO;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonFilter("AttributeGroupDTOFilter")
public class AttributeGroupDTO {
    Long id;
    String name;
    List<AttributeDTO> attributeDTOList;// dùng để hien thị tên thuộc tính ở trang thêm sản phẩm
}
