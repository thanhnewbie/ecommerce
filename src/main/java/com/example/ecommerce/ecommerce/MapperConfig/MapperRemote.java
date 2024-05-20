package com.example.ecommerce.ecommerce.MapperConfig;

import com.example.ecommerce.ecommerce.DAO.*;
import com.example.ecommerce.ecommerce.DTO.*;
import com.example.ecommerce.ecommerce.DTO.Custom.ProductOverviewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

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

    @Mapping(target = "productImageDTOList", source = "entity.productImageList")
    public ProductDTO ProductToProductDTO(Product entity);

    public AttributeProduct AttributeProductDTOToAttributeProduct(AttributeProductDTO dto);

    @Mapping(target = "attributeDTOList", source = "attributeList")
    @Mapping(target = "attributeProductDTOList", source = "attributeProductList")
    public AttributeGroupDTO AttributeGroupToAttributeGroupDTO(AttributeGroup entity);

    public AttributeProductDTO AttributeProductToAttributeProductDTO(AttributeProduct entity);

    public AttributeDTO AttributeToAttributeDTO(Attribute entity);


    @Mapping(target = "attributeProductList", source = "attributeProductDTOList")
    public Sku SkuDTOToSku(SkuDTO dto);

    @Mapping(target = "attributeProductDTOList", source = "attributeProductList")
    public SkuDTO SkuToSkuDTO(Sku entity);
//    @Mapping(target = "galleryList", source = "galleryList")
    public Product ProductDTOToProduct(ProductDTO dto);

    public ProductImage ProductImageDTOToProductImage(ProductImageDTO dto);

}
