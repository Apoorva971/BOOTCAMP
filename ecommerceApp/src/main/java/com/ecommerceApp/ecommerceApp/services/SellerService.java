package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.SellerRepository;
import com.ecommerceApp.ecommerceApp.dtos.AdminSellerDto;
import com.ecommerceApp.ecommerceApp.dtos.SellerRegistrationDto;
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

    public Seller toSeller(SellerRegistrationDto sellerRegistrationDto) {
        Seller seller = modelMapper.map(sellerRegistrationDto, Seller.class);
        return seller;
    }
    public AdminSellerDto toadminSellerDto(Seller seller){
        AdminSellerDto adminSellerDto=modelMapper.map(seller,AdminSellerDto.class);
        return adminSellerDto;
    }
    public List<AdminSellerDto> getAllSeller(String offset, String size, String field){

        Integer pageNo=Integer.parseInt(offset);
        Integer pageSize=Integer.parseInt(size);
        Pageable pageable= PageRequest.of(pageNo,pageSize, Sort.by(field).ascending());
        List<Seller> sellers=sellerRepository.findAll(pageable);
        List<AdminSellerDto> adminSellerDtos=new ArrayList<>();
        sellers.forEach((seller -> adminSellerDtos.add(toadminSellerDto(seller))));
        return adminSellerDtos;

    }
    public AdminSellerDto getSellerByEmail(String email){
        Seller seller=sellerRepository.findByEmail(email);
        AdminSellerDto adminSellerDto=toadminSellerDto(seller);
        return adminSellerDto;
    }

}
