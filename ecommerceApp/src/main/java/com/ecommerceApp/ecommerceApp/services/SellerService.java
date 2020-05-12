package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.AddressRepository;
import com.ecommerceApp.ecommerceApp.Repositories.SellerRepository;
import com.ecommerceApp.ecommerceApp.Repositories.UserRepository;
import com.ecommerceApp.ecommerceApp.Util.PagingAndSortingUtil;
import com.ecommerceApp.ecommerceApp.dtos.*;
import com.ecommerceApp.ecommerceApp.entities.Address;
import com.ecommerceApp.ecommerceApp.entities.ReturnJson;
import com.ecommerceApp.ecommerceApp.entities.Seller;
import com.ecommerceApp.ecommerceApp.entities.Users;
import com.ecommerceApp.ecommerceApp.exceptions.PasswordNotMatchedException;
import com.ecommerceApp.ecommerceApp.exceptions.UserNotFoundException;
import com.ecommerceApp.ecommerceApp.security.AppUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    MessageSource messageSource;
    PagingAndSortingUtil pagingAndSortingUtil = new PagingAndSortingUtil();


    public Seller toSeller(SellerRegistrationDto sellerRegistrationDto) {
        Seller seller = modelMapper.map(sellerRegistrationDto, Seller.class);
        return seller;
    }

    public SellerDto toSellerDto(Seller seller) {
        SellerDto sellerDto = modelMapper.map(seller, SellerDto.class);
        return sellerDto;
    }

    public List<SellerDto> getAllSeller(PagingAndSortingDto pagingAndSortingDto) {
        Pageable pageable = pagingAndSortingUtil.getPageable(pagingAndSortingDto);
        List<Seller> sellers = sellerRepository.findAll(pageable);
        List<SellerDto> sellerDtos = new ArrayList<>();
        sellers.forEach((seller -> sellerDtos.add(toSellerDto(seller))));
        return sellerDtos;
    }

    public SellerDto getSellerByEmail(String email) {
        Seller seller = sellerRepository.findByEmail(email);
        if (email == null) {
            return null;
        }
        SellerDto sellerDto = toSellerDto(seller);
        return sellerDto;
    }

    public SellerViewProfileDto toSellerViewProfileDto(Seller seller) {
        SellerViewProfileDto sellerViewProfileDto = modelMapper.map(seller, SellerViewProfileDto.class);
        return sellerViewProfileDto;
    }

    public SellerViewProfileDto getSellerProfile() {
        Seller seller1 = getLoggedInSeller();
        Seller seller = sellerRepository.findByEmail(seller1.getEmail());
        if (seller.getEmail() == null)
            throw new UserNotFoundException("not found");

        SellerViewProfileDto sellerViewProfileDto = toSellerViewProfileDto(seller);
        return sellerViewProfileDto;
    }

    public ReturnJson registerSeller(SellerRegistrationDto sellerRegistrationDto, Locale locale) {
        if (!(checkIfAllDetailsUnique(sellerRegistrationDto) == "unique")) {
            return new ReturnJson( "Invalid data");
        }
        Seller seller = toSeller(sellerRegistrationDto);
        seller.setPassword(passwordEncoder.encode(seller.getPassword()));
        sellerRepository.save(seller);
        acknowledgementEmail(seller.getEmail());
        return new ReturnJson(messageSource.getMessage("account.created.message", null, locale));

    }

    public boolean isEmailUnique(String email) {
        Seller seller = sellerRepository.findByEmail(email);
        if (seller != null)
            return false;

        return true;
    }

    public boolean isGSTUnique(String GST) {
        Seller seller = sellerRepository.findByGST(GST);
        if (seller != null)
            return false;

        return true;
    }

    public boolean isCompanyNameUnique(String name) {
        Seller seller = sellerRepository.findByCompanyName(name);
        if (seller != null)
            return false;

        return true;
    }

    public String checkIfAllDetailsUnique(SellerRegistrationDto sellerRegistrationDto) {
        if (!isEmailUnique(sellerRegistrationDto.getEmail())) {
            return "Email already exits";
        } else if (!isGSTUnique(sellerRegistrationDto.getGST())) {
            return "GST already exists";
        } else if (!isCompanyNameUnique(sellerRegistrationDto.getCompanyName())) {
            return "Comapny name already exits ";
        } else {
            return "unique";
        }
    }

    public void acknowledgementEmail(String email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Registration confirmation");
        simpleMailMessage.setText("Your account is awaited for activation");
        simpleMailMessage.setTo(email);
        emailSenderService.sendEmail(simpleMailMessage);
    }

    public SellerDto getSellerbyEmail(String email) {
        Seller seller = sellerRepository.findByEmail(email);
        if (email == null) {
            return null;
        }
        SellerDto sellerDto = toSellerDto(seller);
        return sellerDto;
    }

    public SellerViewProfileDto tosellerProfileDto(Seller seller) {
        SellerViewProfileDto sellerViewProfileDto = modelMapper.map(seller, SellerViewProfileDto.class);
        return sellerViewProfileDto;
    }

    public ReturnJson updateSellerProfile(String email, SellerViewProfileDto sellerviewProfileDto, Locale locale) {
        Seller savedSeller = sellerRepository.findByEmail(email);

        if (sellerviewProfileDto.getFirstName() != null)
            savedSeller.setFirstName(sellerviewProfileDto.getFirstName());

        if (sellerviewProfileDto.getLastName() != null)
            savedSeller.setLastName(sellerviewProfileDto.getLastName());

        if (sellerviewProfileDto.getActive() != null && !sellerviewProfileDto.getActive())
            savedSeller.setActive(sellerviewProfileDto.getActive());

        if (sellerviewProfileDto.getGST() != null)
            savedSeller.setGST(sellerviewProfileDto.getGST());

        if (sellerviewProfileDto.getCompanyContact() != null)
            savedSeller.setCompanyContact(sellerviewProfileDto.getCompanyContact());

        if (sellerviewProfileDto.getCompanyName() != null)
            savedSeller.setCompanyName(sellerviewProfileDto.getCompanyName());

        sellerRepository.save(savedSeller);
        return new ReturnJson
                (messageSource.getMessage("seller.update.message", null, locale));
    }

    public ReturnJson updateSellerAddress(String email, Long addressId, AddressDto addressDto, Locale locale) {
        Optional<Address> address = addressRepository.findById(addressId);
        Users user = userRepository.findByEmail(email);

        if (!address.isPresent()) {
            return new ReturnJson("No address found with the given id;");
        }
        Address savedAddress = address.get();
        if (!savedAddress.getUser().getEmail().equals(email)) {
            return new ReturnJson("Invalid Operation");
        }

        if (addressDto.getAddressLine() != null)
            savedAddress.setAddressLine(addressDto.getAddressLine());

        if (addressDto.getCity() != null)
            savedAddress.setCity(addressDto.getCity());

        if (addressDto.getState() != null)
            savedAddress.setState(addressDto.getState());

        if (addressDto.getCountry() != null)
            savedAddress.setCountry(addressDto.getCountry());

        if (addressDto.getZipCode() != null)
            savedAddress.setZipCode(addressDto.getZipCode());

        if (addressDto.getLabel() != null)
            savedAddress.setLabel(addressDto.getLabel());
        addressRepository.save(savedAddress);
        return new ReturnJson( messageSource.getMessage("address.updated.message", null, locale));

    }

    public Seller getLoggedInSeller() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser userDetail = (AppUser) authentication.getPrincipal();
        String username = userDetail.getUsername();
        Seller seller = sellerRepository.findByEmail(username);
        return seller;
    }

    @Transactional
    public void updatePassword(PasswordDto password, Locale locale) {
        Seller seller = getLoggedInSeller();
        String password1 = password.getPassword();
        String confirmPassword = password.getConfirmPassword();
        if (password1.equals(confirmPassword)) {
            seller.setPassword(passwordEncoder.encode(password1));
            sellerRepository.save(seller);
        } else
            throw new PasswordNotMatchedException(messageSource.getMessage("password.notMatched.message", null, locale));
    }
}