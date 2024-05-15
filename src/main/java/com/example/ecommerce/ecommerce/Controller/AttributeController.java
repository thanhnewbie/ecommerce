package com.example.ecommerce.ecommerce.Controller;

import com.example.ecommerce.ecommerce.DAO.AttributeGroup;
import com.example.ecommerce.ecommerce.DTO.AttributeGroupDTO;
import com.example.ecommerce.ecommerce.Repository.AttributeGroupRepository;
import com.example.ecommerce.ecommerce.Service.AttributeGroupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/attribute")
public class AttributeController {
    @Autowired
    AttributeGroupService attributeGroupService;
    @Autowired
    AttributeGroupRepository attributeGroupRepository;
//    @GetMapping("")
//    public MappingJacksonValue getGroupAttributeByCateId(Long id){
//
//        List<AttributeGroupDTO> attributeGroupDTOList = attributeGroupService.findAttributeGroupByCateId(id);
//
//        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
//        filterProvider.addFilter("AttributeGroupDTOFilter", SimpleBeanPropertyFilter.serializeAllExcept("attributeGroupDTOList"));
//        filterProvider.addFilter("AttributeDTOFilter", SimpleBeanPropertyFilter.serializeAllExcept("isDisplay","isAttributeSku"));
//
//        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(attributeGroupDTOList);
//        mappingJacksonValue.setFilters(filterProvider);
//
//        return mappingJacksonValue;
//    }

//    @GetMapping("categoryId")
//    public List<AttributeGroup> getGroupAttributeByCateId(@RequestParam("productId") Long productId, @RequestParam("cateId") Long cateId){// dùng đê lấy thuộc tính danh mục để thêm sản phẩm
//        return attributeGroupService.findAttributeGroupDeepByCateId(productId, cateId);
//    }


}
