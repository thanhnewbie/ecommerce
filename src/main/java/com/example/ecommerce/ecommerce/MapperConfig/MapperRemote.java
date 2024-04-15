package com.example.ecommerce.ecommerce.MapperConfig;

import com.example.ecommerce.ecommerce.DAO.*;
import com.example.ecommerce.ecommerce.DTO.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapperRemote  {
    @Named("IntegerToBoolean")
    public static Boolean IntegerToBoolean(Integer integer){
        return integer == 1;
    }
    @Named("BooleanToInteger")
    public static Integer BooleanToInteger(Boolean boo){
        return boo ? 1 : 0;
    }
    public Category CategoryDTOToCategory(CategoryDTO dto);
    public CategoryDTO CategoryToCategoryDTO(Category entiy);

    @Mapping(target = "price", source = "entity.priceLowest")
    public ProductOverviewDTO ProductToOverview(Product entity);

    @Mapping(target = "galeryList", source = "entity.galleryList")
    public ProductDTO ProductToProductDTO(Product entity);

    @Mapping(target = "attributeId", source = "id.attributeId")
    @Mapping(target = "productId", source = "id.productId")
    @Mapping(target = "name", source = "attributeName")
    public AttributeProductDTO AttributeProductToAttributeProductDTO(AttributeProduct entity);

    @Mapping(target = "attributeName", source = "name")
    @Mapping(target = "id.attributeId", source = "attributeId")
    @Mapping(target = "id.productId", source = "productId")
    public AttributeProduct AttributeProductDTOToAttributeProduct(AttributeProductDTO dto);

    @Mapping(target = "attributeDTOList", source = "attributeList")

    public AttributeGroupDTO AttributeGroupToAttributeGroupDTO(AttributeGroup entity);

    @Mapping(target = "attributeProductDTOList", source = "attributeProductList")
    public AttributeProductGroupDTO AttributeGroupToAttributeProductGroupDTO(AttributeGroup entity);

    public AttributeDTO AttributeToAttributeDTO(Attribute entity);

    @Mapping(target = "attributeDTOList", source = "attributeSkuList")
    public SkuDTO SkuToSkuDTO(Sku entity);
//    @Mapping(target = "attributeSkuList", source = "attributeDTOList")
//    public Sku SkuDTOToSku(SkuDTO dto);

    @Mapping(target = "attributeId", source = "attributeId")
    @Mapping(target = "name", source = "attributeName")
    public AttributeProductDTO AttributeSkuToAttributeProductDTO(AttributeSku entity);

    public Sku SkuDTOToSku(SkuDTO dto);
//    @Mapping(target = "galleryList", source = "galleryList")
    public Product ProductDTOToProduct(ProductDTO dto);

    public Gallery GalleryDTOToGallery(GalleryDTO dto);

}
//Product
//    List<AttributeProduct> attributeProductList;
//
//    List<Sku> skuList;
//
//    List<Gallery> galleryList;

//ProductDTO
//        List<GalleryDTO> galeryList;
//        List<AttributeGroupDTO> attributeGroupDTOList; -> attributeProductList
//        List<SkuDTO> skuDTOList; // chưa cần dùng
//
//        List<AttributeProductDTO> attributeDisplayFistList; // không caần