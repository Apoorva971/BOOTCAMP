package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.CategoryRepository;
import com.ecommerceApp.ecommerceApp.Repositories.ProductRepository;
import com.ecommerceApp.ecommerceApp.Repositories.SellerRepository;
import com.ecommerceApp.ecommerceApp.Util.PagingAndSortingUtil;
import com.ecommerceApp.ecommerceApp.dtos.PagingAndSortingDto;
import com.ecommerceApp.ecommerceApp.dtos.ProductDto;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.ReturnJson;
import com.ecommerceApp.ecommerceApp.entities.Seller;
import com.ecommerceApp.ecommerceApp.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    SellerService sellerService;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    MessageSource messageSource;
    PagingAndSortingUtil pagingAndSortingUtil = new PagingAndSortingUtil();

    ///////////////////////////when user logged in a seller
    public ReturnJson addProduct(Product product,Locale locale) {
        Seller seller = sellerService.getLoggedInSeller();
        product.setSeller(seller);
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject("Regarding Adding of product");
            simpleMailMessage.setFrom("apoorvagarg30@gmail.com");
            simpleMailMessage.setText("Hii Admin, \n There is a pending task for you. Seller \" " +
                    seller.getFirstName() + " added a product '\" " + product.getName());
            simpleMailMessage.setTo(seller.getEmail());
            emailSenderService.sendEmail(simpleMailMessage);
            productRepository.save(product);
        } catch (Exception ex) {
            return new ReturnJson("mail sending failed");
        }
        return new ReturnJson(messageSource.getMessage("product.added.message",null,locale));
    }
    @Cacheable(value = "productCacheSeller",key ="#new")
    public List<ProductDto> viewAllProduct(PagingAndSortingDto pagingAndSortingDto) {
        Pageable pageable = pagingAndSortingUtil.getPageable(pagingAndSortingDto);
        List<Product>productList =  productRepository.findAll(pageable);
        List<ProductDto >productDtos = new ArrayList<>();
        productList.forEach(product -> productDtos.add(new ProductDto(product.getId(),product.getName(),product.getDescription(),product.getBrand(),product.getCategory().getId(),product.getCategory().getName())));
        return productDtos;
    }
    @Transactional
    public ReturnJson deleteProduct(Long productId, Locale locale) {
        Seller seller = sellerService.getLoggedInSeller();
        try {
            Optional<Product> product = productRepository.findByIdAndSellerId(seller.getId(),
                    productId);
            if (product.get().getId() != null) {
                productRepository.deleteByIdAndSellerId(productId, seller.getId());
            }
            return new ReturnJson( messageSource.getMessage("product.deleted.message", null, locale));
        } catch (Exception ex) {
            throw new ProductNotFoundException("Product does not exist");
        }
    }

    @Transactional
    public ReturnJson updateProduct(Long productId, ProductDto productDto, Locale locale) {
        Seller seller = sellerService.getLoggedInSeller();
        Optional<Product> product = productRepository.findByIdAndSellerId(seller.getId(), productId);
        if (!product.isPresent())
            throw new ProductNotFoundException("Product does not exist");
        if (product.get().getBrand() != null) {
            if (productDto.getBrand() != null)
                product.get().setBrand(productDto.getBrand());
            if (productDto.getDescription() != null)
                product.get().setDescription(productDto.getDescription());
            if (productDto.getName() != null)
                product.get().setName(productDto.getName());
            if (productDto.isCancelleable())
                product.get().setCancelleable(productDto.isCancelleable());
            if (productDto.isReturnable())
                product.get().setReturnable(productDto.isReturnable());
            productRepository.save(product.get());
        }
        return new ReturnJson( messageSource.getMessage("product.updated.message", null, locale));

    }

    ///////////////////////////when user logged in as Customer
    public Optional<Product> viewAProduct(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        try {
            Product product1 = product.get();
        } catch (Exception ex) {
            throw new ProductNotFoundException("Product Not Found");
        }
        return product;
    }

    public List<Product> viewAllProductAsCustomer(Long categoryId) {
        List<Product> products = productRepository.findAllByCategoryId(categoryId);
        try {
            products.get(0).getId();
        } catch (Exception ex) {
            throw new ProductNotFoundException("Invalid Category Id");
        }
        return products;
    }

    ///////////////////////////when user logged in as an admin
    public ReturnJson activateProduct(Long productId, Locale locale) {
        Optional<Product> product = productRepository.findById(productId);
        if (!product.get().isActive()) {
            try {
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setSubject("REGARDING PRODUCT ACTIVATION");
                simpleMailMessage.setText("hi your product" + product.get().getName() + "has been activated now you can add variation to it");
                simpleMailMessage.setTo(product.get().getSeller().getEmail());
                emailSenderService.sendEmail(simpleMailMessage);
                productRepository.activateProduct(product.get().getId(), true);
            } catch (Exception ex) {
                return new ReturnJson( "Mail sending Failed... Product is not activated yet... please try again...");
            }
            return new ReturnJson(messageSource.getMessage("product.activated.message", null, locale));
        } else
            return new ReturnJson(messageSource.getMessage("product.alreadyactivated.message", null, locale));
    }

    public ReturnJson deactivateProduct(Long productId, Locale locale) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.get().isActive()) {
            try {
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setSubject("REGARDING PRODUCT DE-ACTIVATION");
                simpleMailMessage.setText("Hi we have found a illegal product that is added by you so we have to deactivate this product" + "Category " + product.get().getCategory().getName() + "name" + product.get().getName() + "Brand" + product.get().getBrand() + "Description" + product.get().getDescription());
                simpleMailMessage.setTo(product.get().getSeller().getEmail());
                emailSenderService.sendEmail(simpleMailMessage);
                productRepository.deActivateProduct(product.get().getId(), false);
            } catch (Exception ex) {
                return new ReturnJson("Mail sending Failed... Product is activated yet... please try again...");
            }
            return new ReturnJson( messageSource.getMessage("product.de-activated.message", null, locale));
        } else
            return new ReturnJson( messageSource.getMessage("product.alreadydeactivated.message", null, locale));
    }
}