package com.example.ecommerce.ecommerce.Service;

import com.example.ecommerce.ecommerce.DAO.AttributeGroup;
import com.example.ecommerce.ecommerce.DAO.AttributeProduct;
import com.example.ecommerce.ecommerce.DTO.AttributeGroupDTO;
import com.example.ecommerce.ecommerce.DTO.AttributeProductDTO;
import com.example.ecommerce.ecommerce.DTO.AttributeProductGroupDTO;
import com.example.ecommerce.ecommerce.MapperConfig.MapperRemote;
import com.example.ecommerce.ecommerce.Repository.AttributeGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttributeGroupService {
    @Autowired
    AttributeGroupRepository attributeGroupRepository;
    @Autowired
    MapperRemote mapperRemote;
    public List<AttributeProduct> attributeGroupDTOToAttributeProductList(AttributeProductGroupDTO attributeProductGroupDTO){
        List<AttributeProduct> attributeProductList = new ArrayList<>();


        AttributeGroup attributeGroup = new AttributeGroup();
        attributeGroup.setId(attributeProductGroupDTO.getId());

        attributeProductList.addAll(attributeProductGroupDTO.getAttributeProductDTOList()
                .stream().map(mapperRemote::AttributeProductDTOToAttributeProduct).collect(Collectors.toList()));
        attributeProductList.forEach(e ->
        {
            e.setAttributeGroup(attributeGroup);
            e.setProductName(null);
        });

        return attributeProductList;
    }
    public List<AttributeGroupDTO> findAttributeGroupByCateId(Long id){
        List<AttributeGroup> attributeGroupList = attributeGroupRepository.findAttributeGroupByCateId(id);
        List<AttributeGroupDTO> attributeGroupDTOList = attributeGroupRepository.findAttributeGroupByCateId(id)
                .stream().map(mapperRemote::AttributeGroupToAttributeGroupDTO).collect(Collectors.toList());
        return attributeGroupDTOList;
    }


}
