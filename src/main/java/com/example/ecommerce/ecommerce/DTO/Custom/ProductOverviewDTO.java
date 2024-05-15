package com.example.ecommerce.ecommerce.DTO.Custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOverviewDTO {
    Long id;
    String name;
    String thumbnail;
    Long price;
}
