package com.example.ecommerce.ecommerce.Service;

import com.example.ecommerce.ecommerce.DAO.Product;
import com.example.ecommerce.ecommerce.DAO.ProductImage;
import com.example.ecommerce.ecommerce.Exception.AppException;
import com.example.ecommerce.ecommerce.Repository.ProductImageRepository;
import com.example.ecommerce.ecommerce.constants.Common;
import com.example.ecommerce.ecommerce.helper.FileHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImageService {
    @Autowired
    StorageService storageService;
    @Autowired
    ProductImageRepository productImageRepository;
    public List<ProductImage> addListProductImage(Long productId, List<MultipartFile> files){
        Product product = new Product();
        product.setId(productId);
        List<ProductImage> productImageList = new ArrayList<>();
        for(MultipartFile file : files){
            storageService.deleteFileByPrefix(productId.toString(), Common.PRODUCT_IMAGE_UPLOAD_PATH);
            String fileNewName = FileHelper.randomUniqueFileName(productId.toString() + file.getOriginalFilename());
            String imageURL = storageService.store(Common.PRODUCT_IMAGE_UPLOAD_PATH, file, fileNewName);
            ProductImage productImage = new ProductImage(null,imageURL, product,null, null);
            ProductImage newProductImage = productImageRepository.save(productImage);
            productImageList.add(newProductImage);
        }
        return productImageList;
    }
    public void deleteByProductId(Long productId){
        productImageRepository.deleteByProductId(productId);
    }
}
