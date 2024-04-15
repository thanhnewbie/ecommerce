package com.example.ecommerce.ecommerce.Service;

import com.example.ecommerce.ecommerce.DAO.Category;
import com.example.ecommerce.ecommerce.DTO.CategoryDTO;
import com.example.ecommerce.ecommerce.MapperConfig.MapperRemote;
import com.example.ecommerce.ecommerce.Repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MapperRemote mapperRemote;
    public List<CategoryDTO> find(){
        return categoryRepository.findAll().stream()
                .map(mapperRemote::CategoryToCategoryDTO).collect(Collectors.toList());
    }

}
