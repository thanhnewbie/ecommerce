package com.example.ecommerce.ecommerce;

import com.example.ecommerce.ecommerce.DAO.*;
import com.example.ecommerce.ecommerce.DTO.CategoryDTO;
import com.example.ecommerce.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.ecommerce.DTO.SkuDTO;
import com.example.ecommerce.ecommerce.MapperConfig.MapperRemote;
import com.example.ecommerce.ecommerce.Repository.*;
import com.example.ecommerce.ecommerce.Service.CategoryService;
import com.example.ecommerce.ecommerce.Service.ProductService;
import org.hibernate.annotations.Collate;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.cfg.CoercionInputShape.Array;

@SpringBootApplication(/*exclude={DataSourceAutoConfiguration.class}*/)
@ComponentScan(basePackages = "com.example.ecommerce.ecommerce")
public class EcommerceApplication implements CommandLineRunner {
	@Autowired
	ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);

	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println("đã chạy được chương trình");


		System.out.println("hello");
	}

}
