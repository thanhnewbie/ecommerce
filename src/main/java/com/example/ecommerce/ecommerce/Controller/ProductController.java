package com.example.ecommerce.ecommerce.Controller;

import com.example.ecommerce.ecommerce.DTO.AttributeGroupDTO;
import com.example.ecommerce.ecommerce.DTO.AttributeProductDTO;
import com.example.ecommerce.ecommerce.DTO.Custom.AttributeProductGroupDTO;
import com.example.ecommerce.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.ecommerce.DTO.Custom.ProductOverviewDTO;
import com.example.ecommerce.ecommerce.DTO.SkuDTO;
import com.example.ecommerce.ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/getProductCommom")
    public ProductDTO getProductById(@RequestParam("id")Long id){
        return productService.findByProductId(id);
    }

    @GetMapping("/getAttribute")
    public List<AttributeGroupDTO> getAttributeGroupDTOByProductId(@RequestParam("id")Long id){
        return productService.getAttributeProductGroupDTOByProductId(id);
    }
    @GetMapping("/getSku")
    public List<SkuDTO> getSkuByProductId(@RequestParam("id")Long id){
        return productService.getSkuByProductId(id);
    }


    @PostMapping("/addProduct/CommonProduct")
    public void addCommonProduct(@RequestParam("categoryId") Long categoryId, @RequestBody ProductDTO productDTO){
         productService.addCommonProduct(categoryId, productDTO);
    }

    @PostMapping("/addProduct/AttributeProduct")
    public void addAttributeProduct(@RequestBody AttributeProductDTO attributeProductDTO){
        productService.addAttributeProduct(attributeProductDTO);
    }

    @PostMapping("/addProduct/Sku")
    public void addSku(@RequestParam("productId") Long productId, @RequestBody SkuDTO skuDTO){
        productService.addSku(productId, skuDTO);
    }

}
