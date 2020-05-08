package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.*;
import com.ecommerceApp.ecommerceApp.Util.PagingAndSortingUtil;
import com.ecommerceApp.ecommerceApp.dtos.PagingAndSortingDto;
import com.ecommerceApp.ecommerceApp.dtos.ProductVariationDto;
import com.ecommerceApp.ecommerceApp.entities.*;
import com.ecommerceApp.ecommerceApp.exceptions.InvalidDetailException;
import com.ecommerceApp.ecommerceApp.exceptions.ProductNotFoundException;
import com.ecommerceApp.ecommerceApp.exceptions.UserNotFoundException;
import com.ecommerceApp.ecommerceApp.exceptions.ValidationException;
import com.ecommerceApp.ecommerceApp.security.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

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
    @Autowired
    MessageSource messageSource;
    PagingAndSortingUtil pagingAndSortingUtil = new PagingAndSortingUtil();

    public Seller getLoggedInSeller() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser userDetail = (AppUser) authentication.getPrincipal();
        String username = userDetail.getUsername();
        Seller seller = sellerRepository.findByEmail(username);
        return seller;
    }

    //////////////////////to get product variation by id being passed
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

    ////////////////////to add a new product variation
    public String addProductVariation(ProductVariationDto productVariationDto) {
        if (!productRepository.findById(productVariationDto.getProductId()).isPresent()) {
            throw new ProductNotFoundException("product with this id does not present");
        }
        Optional<Product> product = productRepository.findById(productVariationDto.getProductId());
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

    //////////////////////////////////to update existing product variation
    public String updateProductVariation(Long productVariationId,
                                         ProductVariationDto productVariationDto, Locale locale
    ) throws IOException {
        ProductVariation saveProductVariation = productVariationRepository.findById(productVariationId).get();
        if (productVariationDto.getPrice() != null)
            saveProductVariation.setPrice(productVariationDto.getPrice());
        if (productVariationDto.getQuantity() != null)
            saveProductVariation.setQuantityAvailable(productVariationDto.getQuantity());
        if (productVariationDto.getActive() != null)
            saveProductVariation.setActive(productVariationDto.getActive());
        if (productVariationDto.getMetadata() != null)
            saveProductVariation.setMetadata(productVariationDto.getMetadata());
        productVariationRepository.save(saveProductVariation);
        return (messageSource.getMessage
                ("productVariation.Updated.message", null, locale));
    }

    /////////////////////////////////////to get all product variation
    public List<ProductVariationDto> getAllProductVariationOfSeller(PagingAndSortingDto pagingAndSortingDto) {

        Pageable pageable = pagingAndSortingUtil.getPageable(pagingAndSortingDto);

        String primaryImageName = "image";

        Long sellerId = getLoggedInSeller().getId();
        Seller seller = sellerRepository.findById(sellerId).get();

        Set<Product> productSet = seller.getProducts(pageable);
        Iterator<Product> productIterator = productSet.iterator();

        List<ProductVariationDto> productVariationDtoList = new ArrayList<>();

        while (productIterator.hasNext()) {
            Product product = productIterator.next();

            Set<ProductVariation> productVariationSet = product.getVariations(pageable);

            if (productVariationSet == null) {
                throw new InvalidDetailException("Currently Logged In Seller Does Not Have Any Product Variation");
            }
            productVariationSet.forEach(productVariation -> productVariationDtoList.add(new ProductVariationDto(
                    productVariation.getProduct().getId(), productVariation.getProduct(), productVariation.getMetadata(), productVariation.getPrice(),
                    productVariation.getQuantityAvailable(), productVariation.getActive(), primaryImageName)));
        }
        return productVariationDtoList;
    }
}
