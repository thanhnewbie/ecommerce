package com.example.ecommerce.ecommerce.Service;

import com.example.ecommerce.ecommerce.DAO.*;
import com.example.ecommerce.ecommerce.DTO.*;
import com.example.ecommerce.ecommerce.DTO.Custom.AttributeProductGroupDTO;
import com.example.ecommerce.ecommerce.DTO.Custom.ProductOverviewDTO;
import com.example.ecommerce.ecommerce.MapperConfig.MapperRemote;
import com.example.ecommerce.ecommerce.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    CategoryRepository categoryRepository;


    public List<ProductOverviewDTO> findAllProductByCate(Long cate){
        List<ProductOverviewDTO> productList = productRepository.findByCategoryId(cate).stream().map(mapperRemote::ProductToOverview).collect(Collectors.toList());
        return productList;
    }

    public ProductDTO findByProductId(Long id){
        ProductDTO productDTO = mapperRemote.ProductToProductDTO(productRepository.findById(id).orElseThrow());


        List<AttributeProductDTO> attributeDTOList = attributeProductRepository.findAttributeByIsDisplay(id)
                        .stream().map(mapperRemote::AttributeProductToAttributeProductDTO)
                        .collect(Collectors.toList());

        productDTO.setAttributeDisplayFistList(attributeDTOList);

        return productDTO;
    }

    public List<AttributeGroupDTO> getAttributeProductGroupDTOByProductId(Long productId){
        List<AttributeGroupDTO> attributeGroupDTOList = attributeGroupRepository.findAttributeGroupByProductId(productId)
                .stream().map(mapperRemote::AttributeGroupToAttributeGroupDTO)
                .collect(Collectors.toList());
        attributeGroupDTOList.stream().forEach(e -> e.getAttributeDTOList().clear());
        return attributeGroupDTOList;
    }

    public List<SkuDTO> getSkuByProductId(Long productId){
        List<SkuDTO> skuList = skuRepository.findByProductId(productId).stream().map(mapperRemote::SkuToSkuDTO).collect(Collectors.toList());
        return skuList;
    }


    @Transactional
    public void addCommonProduct(Long categoryId, ProductDTO productDTO){
        Product product = mapperRemote.ProductDTOToProduct(productDTO);// chuyển một phần sang product


        List<Gallery> galleryList = new ArrayList<>(productDTO.getGaleryList().stream().map(mapperRemote::GalleryDTOToGallery).collect(Collectors.toList()));
        galleryList.forEach(gallery -> gallery.setProduct(product));
        product.setGalleryList(galleryList);


        //List<AttributeProduct> attributeProductList = new ArrayList<>();
        //List<AttributeProductGroupDTO> attributeProductGroupDTOList = productDTO.getAttributeProductGroupDTOList();
        //attributeProductGroupDTOList.forEach(e -> attributeProductList.addAll(attributeGroupService.attributeGroupDTOToAttributeProductList(e)));


        //product.setAttributeProductList(attributeProductList);
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
    @Transactional
    public void addAttributeProduct(AttributeProductDTO attributeProductDTO){
        AttributeProduct attributeProduct = mapperRemote.AttributeProductDTOToAttributeProduct(attributeProductDTO);
        Long attributeProductId = attributeProductRepository.findAttributeProductId(attributeProductDTO.getProductId(), attributeProductDTO.getAttributeId());
        if(attributeProductId != null) attributeProduct.setId(attributeProductId);
        // khởi tạo các entity liên quan và set id cho nó
        Attribute attribute = new Attribute();
        AttributeGroup attributeGroup = new AttributeGroup();
        Product product = new Product();
        attribute.setId(attributeProductDTO.getAttributeId());
        attributeGroup.setId(attributeProductDTO.getAttributeGroupId());
        product.setId(attributeProductDTO.getProductId());

        attributeProduct.setAttribute(attribute);
        attributeProduct.setAttributeGroup(attributeGroup);
        attributeProduct.setProduct(product);


        attributeProductRepository.save(attributeProduct);

    }
    @Transactional
    public void addSku(Long productId, SkuDTO skuDTO){
        Sku sku = mapperRemote.SkuDTOToSku(skuDTO);
        Product product = new Product();
        product.setId(productId);

        sku.setProduct(product);
        skuRepository.save(sku);
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
