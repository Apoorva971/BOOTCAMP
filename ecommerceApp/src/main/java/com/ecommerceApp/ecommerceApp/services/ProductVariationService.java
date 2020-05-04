package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.*;
import com.ecommerceApp.ecommerceApp.dtos.PagingAndSortingDto;
import com.ecommerceApp.ecommerceApp.dtos.ProductVariationDto;
import com.ecommerceApp.ecommerceApp.entities.*;
import com.ecommerceApp.ecommerceApp.exceptions.ProductNotFoundException;
import com.ecommerceApp.ecommerceApp.exceptions.UserNotFoundException;
import com.ecommerceApp.ecommerceApp.exceptions.ValidationException;
import com.ecommerceApp.ecommerceApp.security.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ProductVariationService {
    @Autowired
    ProductVariationRepository productVariationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryMetaDataFieldValuesRepository categoryMetaDataFieldValuesRepository;
    @Autowired
    CategoryMetaDataFieldRepository categoryMetaDataFieldRepository;

    @Autowired
    SellerRepository sellerRepository;

    public Users getLoggedInSeller() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser userDetail = (AppUser) authentication.getPrincipal();
        String username = userDetail.getUsername();
        Users user = userRepository.findByEmail(username);
        return user;
    }


    public ProductVariation getProductVariation(Long variationId) {
        Optional<ProductVariation> productVariation = productVariationRepository.findById(variationId);
        if (!productVariation.isPresent())
            throw new ProductNotFoundException("Product Variation does not exist");
        Product product = productVariation.get().getProduct();
        if (product.isDeleted())
            throw new ProductNotFoundException("Product does not exist");
        Seller seller = product.getSeller();
        if (seller.getEmail().equals(getLoggedInSeller().getEmail())) {
            return productVariation.get();
        } else {
            throw new ProductNotFoundException("User not authorized");
        }
    }

    public String addProductVariation(ProductVariationDto productVariationDto) {
        Optional<Product> product = productRepository.findById(productVariationDto.getProductId());
        if (!product.isPresent() && !product.get().isActive())
            throw new ProductNotFoundException("Product with this id not found");
        Product product1 = product.get();
        if (product1.getSeller().getId() != getLoggedInSeller().getId())
            throw new UserNotFoundException("Product not belong to this seller");
        ProductVariation productVariation = new ProductVariation();
        productVariation.setProduct(product1);
        Optional<Category> category = categoryRepository.findById(product1.getCategory().getId());
        Long id = category.get().getId();
        Map<String, String> metaData = productVariationDto.getMetadata();
        for (Map.Entry<String, String> fieldValues : metaData.entrySet()) {
            String metadatafield = fieldValues.getKey();
            String metadatafieldvalue = fieldValues.getValue();
            CategoryMetaDataField categoryMetadataField = categoryMetaDataFieldRepository.findByName(metadatafield);
            CategoryMetadataFieldValues values = categoryMetaDataFieldValuesRepository.findMetadataFieldValue(categoryMetadataField.getId(), id);
            List<String> stringList = Arrays.asList(values.getMetadataValues().split(","));
            System.out.println(stringList);
            if (stringList.isEmpty()) {
                throw new ProductNotFoundException("Value not found");
            }
        }
        productVariation.setMetadata(productVariationDto.getMetadata());
        if (productVariationDto.getPrice() < 0) {
            throw new ValidationException("Price must be 0 or more");
        }
        productVariation.setPrice(productVariationDto.getPrice());
        if (productVariationDto.getQuantity() < 0) {
            throw new ValidationException("Quantity must be 0 or more");
        }
        productVariation.setQuantityAvailable(productVariationDto.getQuantity());
        productVariation.setPrimaryImageName(productVariationDto.getPrimaryImageName());

        productVariationRepository.save(productVariation);
        return "product variation added successfully";
    }
//
//public List<ProductVariation> findAllVariation(PagingAndSortingDto pagingAndSortingDto,Long productId) {
//    Optional<Product> product=productRepository.findById(productId);
//    if(product.get().isDeleted())
//        throw new ProductNotFoundException("Product does not exist");
//    if(product.isPresent()) {
//        if(getLoggedInSeller().getEmail().equals(product.get().getSeller().getEmail())) {
//            return productVariationRepository.findByProductId(productId, pagingAndSortingDto);
//        }else {
//            throw new ProductNotFoundException("User Not authorized");
//        }
//    } else {
//        throw new ProductNotFoundException("Product not found");
//    }
//}

}
