package com.ecommerceApp.ecommerceApp.security;

import com.ecommerceApp.ecommerceApp.Repositories.*;
import com.ecommerceApp.ecommerceApp.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Bootstrap implements ApplicationRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryMetaDataFieldRepository categoryMetaDataFieldRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    CategoryMetaDataFieldValuesRepository categoryMetaDataFieldValuesRepository;
    @Autowired
    ProductVariationRepository productVariationRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AdminRepository adminRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Role admin = new Role(1l, "ROLE_ADMIN");
        Role seller = new Role(2l, "ROLE_SELLER");
        Role customer = new Role(3l, "ROLE_CUSTOMER");

        roleRepository.save(admin);
        roleRepository.save(seller);
        roleRepository.save(customer);

if(userRepository.count()<1) {
    Admin admin1 = new Admin("apoorvagarg30@gmail.com", "Apoorva", "", "Garg");
    admin1.setPassword(passwordEncoder.encode("apoorvagarg"));

    admin1.isEnabled(true);
    admin1.setAccountNonExpired(true);
    admin1.setAccountNonLocked(true);
    admin1.setCredentialsNonExpired(true);
    admin1.setEnabled(true);
    admin1.setActive(true);
    admin1.addRole(admin);
    admin1.addRole(seller);
    admin1.addAddress(new Address("1153", "Bulanshahar", "Uttar Pradesh", "202394", "India", "Home"));
    admin1.setActive(true);
    Set<Role> roleSet = new HashSet<>();
    roleSet.add(admin);
    admin1.setRoles(roleSet);
    adminRepository.save(admin1);

    Customer customer1 = new Customer("daljitkalsi@gmail.com", "Daljit", "",
            "kalsi", "9837564567");

    customer1.setPassword(passwordEncoder.encode("daljitkalsi"));
    customer1.addAddress(new Address("112", "Punjab", "Punjab", "12345", "India", "P.G"));
    customer1.addAddress(new Address("101", "Chandigarh", "Punjab", "202344", "India", "Home"));

    customer1.isEnabled(true);
    customer1.setAccountNonExpired(true);
    customer1.setAccountNonLocked(true);
    customer1.setCredentialsNonExpired(true);
    customer1.setEnabled(true);
    customer1.setActive(true);

    Set<Role>roleCustomer = new HashSet<>();
    roleCustomer.add(customer);
    customer1.setRoles(roleCustomer);
    customerRepository.save(customer1);

    Seller seller1 = new Seller("apoorvagarg0@gmail.com", "Yash", ""
            , "bhatia", "yfwbeu72384627", "574148715478",
            "ttn");
    seller1.setPassword(passwordEncoder.encode("yashbhatia"));
    seller1.addAddress(new Address("110", "Dadri", "Uttar Pradesh", "101310", "India", "office"));

    seller1.isEnabled(true);
    seller1.setAccountNonExpired(true);
    seller1.setAccountNonLocked(true);
    seller1.setCredentialsNonExpired(true);
    seller1.setEnabled(true);
    seller1.setActive(true);

    Set<Role>roleSeller  = new HashSet<>();
    roleSeller.add(seller);
    seller1.setRoles(roleSeller);
    sellerRepository.save(seller1);

    System.out.println("Total users saved::" + userRepository.count());
}
        if (categoryRepository.count() < 1) {
            Category electronics = new Category("Electronics");
            categoryRepository.save(electronics);
            Category phones = new Category("Phones");
            phones.setParentCategory(electronics);
            categoryRepository.save(phones);
            Category laptop = new Category("Laptop");
            laptop.setParentCategory(electronics);
            categoryRepository.save(laptop);
        }
        if (productRepository.count() < 1) {
            Product vivoV5 = new Product("Vivo V5 Plus","smart-phone","Vivo", true, true);
            vivoV5.setSeller(sellerRepository.findByEmail("apoorvagarg0@gmail.com"));
            vivoV5.setCategory(categoryRepository.findByName("phones"));

            productRepository.save(vivoV5);

            Product oneplus = new Product("OnePlus7", "smart-phone", "OnePlus", true, true);
            oneplus.setSeller(sellerRepository.findByEmail("apoorvagarg0@gmail.com"));
            oneplus.setCategory(categoryRepository.findByName("phones"));

            productRepository.save(oneplus);

            Product hplaptop = new Product("HP", "smart-laptop", "HP", true, true);
            hplaptop.setSeller(sellerRepository.findByEmail("apoorvagarg0@gmail.com"));
            hplaptop.setCategory(categoryRepository.findByName("laptop"));

            productRepository.save(hplaptop);
        }

        if (categoryMetaDataFieldRepository.count() < 1) {
            CategoryMetaDataField ram = new CategoryMetaDataField("Ram");
            categoryMetaDataFieldRepository.save(ram);
            CategoryMetaDataField camera = new CategoryMetaDataField("Camera");
            categoryMetaDataFieldRepository.save(camera);
            CategoryMetaDataField internalMemory = new CategoryMetaDataField("Internal Memory");
            categoryMetaDataFieldRepository.save(internalMemory);
        }

        if (categoryMetaDataFieldValuesRepository.count() < 1) {
            CategoryMetadataFieldValues ram = new CategoryMetadataFieldValues(
                    categoryMetaDataFieldRepository.findByName("Ram"),
                    categoryRepository.findByName("phones"),
                    "32GB,64GB,128GB");

            categoryMetaDataFieldValuesRepository.save(ram);

            CategoryMetadataFieldValues camera = new CategoryMetadataFieldValues(
                    categoryMetaDataFieldRepository.findByName("Camera"),
                    categoryRepository.findByName("phones"), "64pixel,128pixel");

            categoryMetaDataFieldValuesRepository.save(camera);

            CategoryMetadataFieldValues internalMemory = new CategoryMetadataFieldValues(
                    categoryMetaDataFieldRepository.findByName("Internal Memory"),
                    categoryRepository.findByName("phones"), "128GB,256GB,64GB");

            categoryMetaDataFieldValuesRepository.save(internalMemory);

        }
        if (productVariationRepository.count() < 1) {
            Product vivoV5 = productRepository.findByName("Vivo V5 Plus");
            ProductVariation vivov5variant = new ProductVariation(25l, 2500l);
            vivov5variant.setProduct(vivoV5);
            Map<String,String> metadata = new HashMap<>();
            metadata.put("Ram","32GB");
            metadata.put("Internal Memory","128GB");
            vivov5variant.setMetadata(metadata);
            vivov5variant.setActive(true);
            productVariationRepository.save(vivov5variant);
            Product oneplus = productRepository.findByName("OnePlus7");
            ProductVariation oneplus7variant = new ProductVariation(20l,30000l);
            oneplus7variant.setProduct(oneplus);
            Map<String,String> metadata1 = new HashMap<>();
            metadata1.put("Ram","64GB");
            metadata1.put("Internal Memory","256GB");
            oneplus7variant.setMetadata(metadata1);
            oneplus7variant.setActive(true);
            productVariationRepository.save(oneplus7variant);
        }
    }
}
