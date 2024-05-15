package com.example.ecommerce.ecommerce.Controller;

import com.example.ecommerce.ecommerce.DTO.CategoryDTO;
import com.example.ecommerce.ecommerce.DTO.Custom.ProductOverviewDTO;
import com.example.ecommerce.ecommerce.Service.CategoryService;
import com.example.ecommerce.ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/category")
public class CatgoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("/getAllCategory")
    List<CategoryDTO> get(){
        return categoryService.find();
    }
    @GetMapping("/AllProduct")
    List<ProductOverviewDTO> getAllProductByCate(@RequestParam("id") Long id){
        return productService.findAllProductByCate(id);
    }

}
