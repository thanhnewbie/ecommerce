package com.example.ecommerce.ecommerce.Service;

import com.example.ecommerce.ecommerce.DAO.AttributeProduct;
import com.example.ecommerce.ecommerce.DAO.Gallery;
import com.example.ecommerce.ecommerce.DAO.Product;
import com.example.ecommerce.ecommerce.DTO.*;
import com.example.ecommerce.ecommerce.MapperConfig.MapperRemote;
import com.example.ecommerce.ecommerce.Repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    GalleryRepository galleryRepository;
    @Autowired
    AttributeProductRepository attributeProductRepository;
    @Autowired
    MapperRemote mapperRemote;
    @Autowired
    AttributeGroupService attributeGroupService;
    @Autowired
    AttributeGroupRepository attributeGroupRepository;
    @Autowired
    SkuRepository skuRepository;


    public List<ProductOverviewDTO> findAllProductByCate(Long cate){
        List<ProductOverviewDTO> productList = productRepository.findByCategoryId(cate).stream().map(mapperRemote::ProductToOverview).collect(Collectors.toList());
        return productList;
    }

    public ProductDTO findByProductId(Long id){
        ProductDTO productDTO = mapperRemote.ProductToProductDTO(productRepository.findById(id).orElseThrow());

        List<AttributeProductGroupDTO> attributeGroupDTOList = attributeGroupRepository.findAttributeByProductId(id)
                .stream().map(mapperRemote::AttributeGroupToAttributeProductGroupDTO)
                .collect(Collectors.toList());

        List<SkuDTO> skuList = skuRepository.findByProductId(id).stream().map(mapperRemote::SkuToSkuDTO).collect(Collectors.toList());
        List<AttributeProductDTO> attributeDTOList = attributeProductRepository.findAttributeByIsDisplay(id)
                        .stream().map(mapperRemote::AttributeProductToAttributeProductDTO)
                        .collect(Collectors.toList());

        productDTO.setAttributeProductGroupDTOList(attributeGroupDTOList);
        productDTO.setSkuDTOList(skuList);
        productDTO.setAttributeDisplayFistList(attributeDTOList);

        return productDTO;
    }
    @Transactional
    public void addProduct(Long categoryId, ProductDTO productDTO){
        Product product = mapperRemote.ProductDTOToProduct(productDTO);// chuyển một phần sang product


        List<Gallery> galleryList = new ArrayList<>(productDTO.getGaleryList().stream().map(mapperRemote::GalleryDTOToGallery).collect(Collectors.toList()));
        galleryList.forEach(gallery -> gallery.setProduct(product));
        product.setGalleryList(galleryList);


        List<AttributeProduct> attributeProductList = new ArrayList<>();
        List<AttributeProductGroupDTO> attributeProductGroupDTOList = productDTO.getAttributeProductGroupDTOList();
        attributeProductGroupDTOList.forEach(e -> attributeProductList.addAll(attributeGroupService.attributeGroupDTOToAttributeProductList(e)));


        product.setAttributeProductList(attributeProductList);
        Product productTmp = productRepository.save(product);

        galleryList.forEach(g -> {
            g.setProduct(productTmp);
            galleryRepository.save(g);
        });
//        attributeProductList.forEach(a -> {
//            a.getId().setProduct(product);
//            attributeProductRepository.save(a);
//        });


    }


}

//    Product product = mapperRemote.ProductDTOToProduct(productDTO);// chuyển một phần sang product
//
//
//
//    List<Gallery> galleryList = new ArrayList<>(productDTO.getGaleryList().stream().map(mapperRemote::GalleryDTOToGallery).collect(Collectors.toList()));
//        galleryList.forEach(gallery -> gallery.setProduct(product));
//                product.setGalleryList(galleryList);
//
//
//                List<AttributeProduct> attributeProductList = new ArrayList<>();
//        List<AttributeProductGroupDTO> attributeProductGroupDTOList = productDTO.getAttributeProductGroupDTOList();
//        attributeProductGroupDTOList.forEach(e -> attributeProductList.addAll(attributeGroupService.attributeGroupDTOToAttributeProductList(e)));
//
//
//        product.setAttributeProductList(attributeProductList);
//
//        attributeProductList.forEach(a -> a.setProduct(product));
//        productRepository.save(product);
