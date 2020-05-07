package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.CategoryRepository;
import com.ecommerceApp.ecommerceApp.Repositories.ProductRepository;
import com.ecommerceApp.ecommerceApp.Repositories.SellerRepository;
import com.ecommerceApp.ecommerceApp.dtos.ProductDto;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.Seller;
import com.ecommerceApp.ecommerceApp.exceptions.InvalidDetailException;
import com.ecommerceApp.ecommerceApp.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    ///////////////////////////when user logged in a seller
    public String addProduct(Product product) {
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
                return "mail sending failed";
            }
            return "product added successfully";
        }

    public Optional<Product> viewProduct(Long productId) {
        Seller seller = sellerService.getLoggedInSeller();
        Optional<Product> product = productRepository.findByIdAndSellerId(seller.getId(), productId);
        if (product.get().getId() != null)
            if (!product.get().isDeleted())
                return product;
            else
                throw new ProductNotFoundException("Product has been deleted from the database");
        else
            throw new ProductNotFoundException("Product does not exist");
    }

    public List<Product> viewAllProductAsSeller() {
        Seller seller = sellerService.getLoggedInSeller();
        return productRepository.findAllBySeller(seller.getId(),
                PageRequest.of(0, 10, Sort.Direction.ASC, "id"));

    }
    @Transactional
    public String deleteProduct(Long productId, Locale locale) {
        Seller seller = sellerService.getLoggedInSeller();
        try {
            Optional<Product> product = productRepository.findByIdAndSellerId( seller.getId(),
                    productId);
            if(product.get().getId() !=null){
                productRepository.deleteByIdAndSellerId(productId,seller.getId());
            }
            return messageSource.getMessage("product.deleted.message",null,locale);
        } catch (Exception ex) {
            throw new ProductNotFoundException("Product does not exist");
        }
    }

    @Transactional
    public String updateProduct(Long productId, ProductDto productDto, Locale locale) {
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
            // Product product1 = product.get();
            productRepository.save(product.get());
        }
        return messageSource.getMessage("product.updated.message", null, locale);

    }
    ///////////////////////////when user logged in as Customer
    public Optional<Product> viewProductAsCustomer(Long productId) {
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
    public Optional<Product> viewAProductAsAdmin(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        try {
            Product product1 = product.get();
        } catch (Exception ex) {
            throw new ProductNotFoundException("Product Not Found");
        }
        return product;
    }

    public List<Product> viewAllProductsAsAdmin(Long categoryId) {
        List<Product> products = productRepository.findAllByCategoryId(categoryId);
        try {
            products.get(0).getId();
        } catch (Exception ex) {
            throw new ProductNotFoundException("Invalid Category Id");
        }
        return products;
    }
    public String activateProduct(Long productId,Locale locale) {
        Optional<Product> product=productRepository.findById(productId);
        if(!product.get().isActive()){
            try {
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setSubject("REGARDING PRODUCT ACTIVATION");
                simpleMailMessage.setText("hi your product"+product.get().getName()+"has been activated now you can add variation to it");
                simpleMailMessage.setTo(product.get().getSeller().getEmail());
                emailSenderService.sendEmail(simpleMailMessage);
                productRepository.activateProduct(product.get().getId(),true);
            }
            catch (Exception ex) {
                return "Mail sending Failed... Product is not activated yet... please try again...";
            }
            return messageSource.getMessage("product.activated.message",null,locale);
        }
        else
            return messageSource.getMessage("product.alreadyactivated.message",null,locale);
    }
    public String deactivateProduct(Long productId,Locale locale) {
        Optional<Product> product=productRepository.findById(productId);
        if(product.get().isActive()){
            try {
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setSubject("REGARDING PRODUCT ACTIVATION");
                simpleMailMessage.setText("Hi we have found a illegal product that is added by you so we have to deactivate this product"+"Category "+product.get().getCategory().getName()+"name"+product.get().getName()+"Brand"+product.get().getBrand()+"Description"+product.get().getDescription());
                simpleMailMessage.setTo(product.get().getSeller().getEmail());
                emailSenderService.sendEmail(simpleMailMessage);
                productRepository.deActivateProduct(product.get().getId(),false);
            }
            catch (Exception ex) {
                return "Mail sending Failed... Product is activated yet... please try again...";
            }
            return messageSource.getMessage("product.de-activated.message",null,locale);
        }
        else
            return messageSource.getMessage("product.alreadydeactivated.message",null,locale);
    }
}