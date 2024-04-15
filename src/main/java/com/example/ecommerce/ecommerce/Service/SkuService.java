package com.example.ecommerce.ecommerce.Service;

import com.example.ecommerce.ecommerce.DAO.Sku;
import com.example.ecommerce.ecommerce.DTO.SkuDTO;
import com.example.ecommerce.ecommerce.MapperConfig.MapperRemote;
import com.example.ecommerce.ecommerce.Repository.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkuService {
    @Autowired
    MapperRemote mapperRemote;
    @Autowired
    SkuRepository skuRepository;
//    public Long addSkuDTO(SkuDTO skuDTO, Long productID){
//        Sku sku = mapperRemote.SkuDTOToSku(skuDTO);
//        Sku tmp = skuRepository.save(sku);
//        return tmp.getId();
//    }
}
