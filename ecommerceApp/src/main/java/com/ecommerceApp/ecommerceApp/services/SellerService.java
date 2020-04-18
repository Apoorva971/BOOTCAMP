package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.SellerRepository;
import com.ecommerceApp.ecommerceApp.dtos.CustomerViewProfileDto;
import com.ecommerceApp.ecommerceApp.dtos.SellerDto;
import com.ecommerceApp.ecommerceApp.dtos.SellerRegistrationDto;
import com.ecommerceApp.ecommerceApp.dtos.SellerViewProfileDto;
import com.ecommerceApp.ecommerceApp.entities.Customer;
import com.ecommerceApp.ecommerceApp.entities.Seller;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    EmailSenderService emailSenderService;

    public Seller toSeller(SellerRegistrationDto sellerRegistrationDto) {
        Seller seller = modelMapper.map(sellerRegistrationDto, Seller.class);
        return seller;
    }

    public SellerDto toSellerDto(Seller seller) {
        SellerDto sellerDto = modelMapper.map(seller, SellerDto.class);
        return sellerDto;
    }

    public List<SellerDto> getAllSeller(String offset, String size, String field) {

        Integer pageNo = Integer.parseInt(offset);
        Integer pageSize = Integer.parseInt(size);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(field).ascending());
        List<Seller> sellers = sellerRepository.findAll(pageable);
        List<SellerDto> sellerDtos = new ArrayList<>();
        sellers.forEach((seller -> sellerDtos.add(toSellerDto(seller))));
        return sellerDtos;

    }

    public SellerDto getSellerByEmail(String email) {
        Seller seller = sellerRepository.findByEmail(email);
        SellerDto sellerDto = toSellerDto(seller);
        return sellerDto;
    }
    public SellerViewProfileDto toSellerViewProfileDto(Seller seller){
        SellerViewProfileDto sellerViewProfileDto = modelMapper.map(seller,SellerViewProfileDto.class);
        return sellerViewProfileDto;
    }
    public SellerViewProfileDto getSellerProfile(String email){
        Seller seller = sellerRepository.findByEmail(email);
        SellerViewProfileDto sellerViewProfileDto = toSellerViewProfileDto(seller);
        return sellerViewProfileDto;
    }
}
//    public boolean isEmailUnique(String email){
//        Seller seller = sellerRepository.findByEmail(email);
//        if(seller != null)
//            return false;
//
//        return true;
//    }
//    public boolean isGSTUnique(String GST){
//        Seller seller = sellerRepository.findByGST(GST);
//        if(seller != null)
//            return false;
//
//        return true;
//    }
//    public boolean isCompanyNameUnique(String name){
//        Seller seller = sellerRepository.findByCompanyName(name);
//        if(seller != null)
//            return false;
//
//        return true;
//    }
//    public String checkIfUnique(SellerRegistrationDto sellerRegistrationDto){
//        if(!isEmailUnique(sellerRegistrationDto.getEmail())){
//            return "Email already exits";
//        }
//        else if(!isGSTUnique(sellerRegistrationDto.getGST())){
//            return "GST already exists";
//        }
//        else if(!isCompanyNameUnique(sellerRegistrationDto.getCompanyName())){
//            return "Comapny name already exits ";
//        }
//        else{
//            return "unique";
//        }
//    }
//    public void acknowledgementEmail(String email){
//        String subject="Registration confirmation";
//        String text="Your account is awaited for confirmation";
//        mailService.sendEmail(email,subject,text);
//    }
//    public SellerDto getSellerByEmaiId(String email){
//        Seller seller=sellerRepository.findByEmail(email);
//        if(email==null){
//            return null;
//        }
//        SellerDto sellerDto =toadminSellerDto(seller);
//        return sellerDto;
//    }
//
//
//}