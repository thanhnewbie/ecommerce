package com.example.ecommerce.ecommerce.Controller;

import com.example.ecommerce.ecommerce.DAO.Product;
import com.example.ecommerce.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("")
    public ProductDTO getProductById(@RequestParam("id")Long id){
        return productService.findByProductId(id);
    }

    @PostMapping("/addProduct")
    public void addProduct(@RequestParam("categoryId") Long categoryId, @RequestBody ProductDTO productDTO){
         productService.addProduct(categoryId, productDTO);

    }
}
