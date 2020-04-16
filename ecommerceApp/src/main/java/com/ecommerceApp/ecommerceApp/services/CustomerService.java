package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.CustomerRepository;
import com.ecommerceApp.ecommerceApp.dtos.CustomerDto;
import com.ecommerceApp.ecommerceApp.dtos.CustomerRegistrationDto;
import com.ecommerceApp.ecommerceApp.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;


    public Customer toCustomer(CustomerRegistrationDto customerRegistrationDto) {
        Customer customer = modelMapper.map(customerRegistrationDto, Customer.class);
        return customer;
    }

    public CustomerDto toCustomerDto(Customer customer) {
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setMiddleName(customer.getMiddleName());
        customerDto.setLastName(customer.getLastName());
        return customerDto;
    }

    public List<CustomerDto> getAllCustomers(String offset, String size, String field) {
        Integer pageNo = Integer.parseInt(offset);
        Integer pageSize = Integer.parseInt(size);

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(field).ascending());

        List<Customer> customers = customerRepository.findAll(pageable);

        List<CustomerDto> customerAdminApiDtos = new ArrayList<>();

        customers.forEach((customer) -> customerAdminApiDtos.add(toCustomerDto(customer)));
        return customerAdminApiDtos;
    }

    public CustomerDto getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email);
        CustomerDto customerDto =toCustomerDto(customer);
        return customerDto;
    }
}