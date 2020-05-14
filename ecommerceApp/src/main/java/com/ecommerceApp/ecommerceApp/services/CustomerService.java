package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.*;
import com.ecommerceApp.ecommerceApp.Util.PagingAndSortingUtil;
import com.ecommerceApp.ecommerceApp.dtos.*;
import com.ecommerceApp.ecommerceApp.entities.*;
import com.ecommerceApp.ecommerceApp.exceptions.EmailAlreadyExistsException;
import com.ecommerceApp.ecommerceApp.exceptions.PasswordNotMatchedException;
import com.ecommerceApp.ecommerceApp.exceptions.ServerException;
import com.ecommerceApp.ecommerceApp.exceptions.UserNotFoundException;
import com.ecommerceApp.ecommerceApp.security.AppUser;
import com.ecommerceApp.ecommerceApp.security.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

@Component
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    AddressService addressService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    MessageSource messageSource;

    PagingAndSortingUtil pagingAndSortingUtil = new PagingAndSortingUtil();


    Customer initializeNewCustomer(Customer customer) {
        customer.setActive(false);
        customer.setDeleted(false);
        customer.setAccountNonExpired(true);
        customer.setAccountNonLocked(true);
        customer.setCredentialsNonExpired(true);
        customer.setEnabled(true);
        passwordEncoder.encode(customer.getPassword());
        return customer;
    }

    private ModelMapper modelMapper = new ModelMapper();

    public ReturnJson createNewCustomer(CustomerRegistrationDto customerRegistrationDto, Locale locale) throws
            EmailAlreadyExistsException {

        Customer customer = modelMapper.map(customerRegistrationDto, Customer.class);
        if (customerRepository.findByEmail(customer.getEmail()) != null) // User already exists with this email
            throw new EmailAlreadyExistsException("User already exists with email : " + customer.getEmail());
        else if (customer.getPassword().equals(customer.getPassword())) {
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(roleRepository.findByAuthority("ROLE_CUSTOMER"));
            customer.setRoles(roleSet);
            customer.setEnabled(true);
            customer.setAccountNonExpired(true);
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            customerRepository.save(initializeNewCustomer(customer));   // saving the Customer

            VerificationToken verificationToken = new VerificationToken(customer);
            verificationToken.setEmail(customer.getEmail());
            verificationTokenRepository.save(verificationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setTo(customer.getEmail());
            mailMessage.setSubject("Complete Registration of the Customer!");
            mailMessage.setFrom("apoorvagarg30@gmail.com");
//            mailMessage.setText("To confirm your account, please click on the Link given below : "
//                    + "http://localhost:8080/register/confirm?token=" + verificationToken.getToken());
            mailMessage.setText("Hii,  To confirm your account, please click here : "
                    + "http://localhost:8080/register/activate?token=" + verificationToken.getToken());
            emailSenderService.sendEmail(mailMessage);
        } else {
            return new ReturnJson("if you don't get email click here -> http://localhost:8080/re-sent-link/" + customer.getEmail());
        }
        return new ReturnJson(messageSource.getMessage
                ("customer.registered.message", null, locale));
    }

    public ReturnJson validateRegistrationToken(String userToken) {

        VerificationToken foundToken = verificationTokenRepository.findByToken(userToken);

        if (foundToken != null) {
            Customer customer = customerRepository.findByEmail(foundToken.getEmail());
            customer.setEnabled(true);
            customerRepository.save(customer);
            return new ReturnJson("account verified");
        }
        return new ReturnJson("something went wrong!");
    }

    public ReturnJson activateCustomer(String userToken, Locale locale) {
        VerificationToken token1 = verificationTokenRepository.findByToken(userToken);
        if (token1 != null) {
            Customer customer = customerRepository.findByEmail(token1.getEmail());
            if (customer.getActive() == false) {
                customer.setActive(true);
                customer.setEnabled(true);
                userRepository.save(customer);
                customerRepository.save(customer);
                return new ReturnJson("Your account has been activated");
            } else {
                return new ReturnJson(messageSource.getMessage("account.alreadyactivated.message", null, locale));
            }
        } else {
            return new ReturnJson("http://localhost:8080/register/activate" + userToken + " has been expired.");
        }
    }

    @Transactional
    public ReturnJson reSentLink(String email) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setEmail(email);
        verificationToken.setCreatedDate(new Date());
        verificationToken.setExpiryDate(new Date());
        System.out.println(verificationToken.getToken());
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject("ACCOUNT ACTIVATE TOKEN");
            simpleMailMessage.setText("Hii, To confirm your account, please click here : "
                    + "http://localhost:8080/register/activate?token=" + verificationToken.getToken());
            simpleMailMessage.setTo(verificationToken.getEmail());
            emailSenderService.sendEmail(simpleMailMessage);
            verificationTokenRepository.save(verificationToken);
            return new ReturnJson("Check your email for further registration process");
        } catch (Exception ex) {
            throw new ServerException("There is some error while connecting you," +
                    " please click again to re-sent activation link-> http://localhost:8080/re-sent-link/" + email);
        }
    }

    public CustomerDto toCustomerDto(Customer customer) {
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        return customerDto;
    }

    public List<CustomerDto> getAllCustomer(PagingAndSortingDto pagingAndSortingDto) {
//        Integer pageNo = Integer.parseInt(offset);
//        Integer pageSize = Integer.parseInt(size);
//        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(field).ascending());
        Pageable pageable = pagingAndSortingUtil.getPageable(pagingAndSortingDto);
        List<Customer> customers = customerRepository.findAll(pageable);
        List<CustomerDto> customerDto = new ArrayList<>();
        customers.forEach(customer -> customerDto.add(toCustomerDto(customer)));
        return customerDto;
    }

    public CustomerViewProfileDto toCustomerViewProfile(Customer customer) {
        CustomerViewProfileDto customerViewProfileDto = modelMapper.map(customer, CustomerViewProfileDto.class);
        return customerViewProfileDto;
    }

    public CustomerViewProfileDto getcustomerProfile(String email) {

        Customer customer = customerRepository.findByEmail(email);

        if (customer == null)
            throw new UserNotFoundException("not found");

        CustomerViewProfileDto customerViewProfileDto = toCustomerViewProfile(customer);

        return customerViewProfileDto;
    }

    public ReturnJson updateCustomerProfile(String email, CustomerViewProfileDto customerViewProfileDto, Locale locale) {
        Customer customer = getLoggedInCustomer();
        if (customerViewProfileDto.getFirstName() != null)
            customer.setFirstName(customerViewProfileDto.getFirstName());
        if (customerViewProfileDto.getMiddleName() != null)
            customer.setMiddleName(customerViewProfileDto.getMiddleName());
        if (customerViewProfileDto.getLastName() != null)
            customer.setLastName(customerViewProfileDto.getLastName());
        if (customerViewProfileDto.getContact() != null)
            customer.setContact(customerViewProfileDto.getContact());
        customerRepository.save(customer);
        return new ReturnJson(messageSource.getMessage("customer.update.message", null, locale));
    }

    public Set getCustomerAllAdress(String email) {
        Customer customer = customerRepository.findByEmail(email);
        Set<AddressDto> addressDtoSet = new HashSet<>();
        Set<Address> addresses = customer.getAddresses();
        addresses.forEach(address -> addressDtoSet.add(addressService.toAddressDto(address)));
        return addressDtoSet;
    }

    public ReturnJson addNewAddress(String email, AddressDto addressDto, Locale locale) {
        Customer customer = customerRepository.findByEmail(email);
        Address newAddress = addressService.toAddress(addressDto);
        customer.addAddress(newAddress);
        customerRepository.save(customer);
        return new ReturnJson(messageSource.getMessage("address.added.message", null, locale));

    }

    @Transactional
    public ReturnJson deleteAddress(String email, Long id, Locale locale) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (!addressOptional.isPresent()) {
            return new ReturnJson(messageSource.getMessage("address.invalidId.message", null, locale));
        }
        Address savedAddress = addressOptional.get();
        if (savedAddress.getUser().getEmail().equals(email)) {
            addressRepository.deleteById(id);
            return new ReturnJson(messageSource.getMessage("address.deleted.message", null, locale));

        }
        return new ReturnJson(messageSource.getMessage("address.deleted.message", null, locale));
    }

    public ReturnJson updateCustomerAddress(String username, AddressDto addressDto, Long id, Locale locale) {
        Optional<Address> address = addressRepository.findById(id);
        if (!address.isPresent())
            return new ReturnJson(messageSource.getMessage("address.invalidId.message", null, locale));
        Address savedAddress = address.get();
        Users users = userRepository.findByEmail(username);
        if (!savedAddress.getUser().getEmail().equals(username))
            return new ReturnJson(messageSource.getMessage("address.notPresent.message", null, locale));
        if (addressDto.getCity() != null)
            savedAddress.setCity(addressDto.getCity());
        if (addressDto.getState() != null)
            savedAddress.setState(addressDto.getCity());
        if (addressDto.getCountry() != null)
            savedAddress.setCountry(addressDto.getCountry());
        if (addressDto.getZipCode() != null)
            savedAddress.setZipCode(addressDto.getZipCode());
        if (addressDto.getLabel() != null)
            savedAddress.setLabel(addressDto.getLabel());
        if (addressDto.getAddressLine() != null)
            savedAddress.setLabel(addressDto.getAddressLine());
        addressRepository.save(savedAddress);
        return new ReturnJson(messageSource.getMessage("address.updated.message", null, locale));
    }

    public Customer getLoggedInCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser userDetail = (AppUser) authentication.getPrincipal();
        String username = userDetail.getUsername();
        Customer customer = customerRepository.findByEmail(username);
        return customer;
    }

    @Transactional
    public void updateCustomerPassword(PasswordDto password, Locale locale) {
        Customer customer = getLoggedInCustomer();
        String password1 = password.getPassword();
        String confirmPassword = password.getConfirmPassword();
        if (password1.equals(confirmPassword)) {
            customer.setPassword(passwordEncoder.encode(password1));
            customerRepository.save(customer);
        } else
            throw new PasswordNotMatchedException(messageSource.getMessage("password.notMatched.message", null, locale));
    }
}
