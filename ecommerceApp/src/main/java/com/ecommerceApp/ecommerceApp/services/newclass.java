package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.entities.Users;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

//package com.ecommerceApp.ecommerceApp.services;
//
//import ch.qos.logback.core.status.Status;
//import com.ecommerceApp.ecommerceApp.Repositories.AddressRepository;
//import com.ecommerceApp.ecommerceApp.Repositories.CustomerRepository;
//import com.ecommerceApp.ecommerceApp.Repositories.ProductRepository;
//import com.ecommerceApp.ecommerceApp.Repositories.ProductVariationRepository;
//import com.ecommerceApp.ecommerceApp.entities.*;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.management.Notification;
//import java.util.Optional;
//
@Service
public class newclass {
    @PersistenceContext
    private EntityManager entityManager;

//    public CriteriaImpl(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }


    //    @Override
    public Users findById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Users> criteriaQuery = criteriaBuilder.createQuery(Users.class);

        Root<Users> userRoot = criteriaQuery.from(Users.class);
        Predicate idPredicate = criteriaBuilder.equal(userRoot.get("id"), id);
        System.out.println("id ......." + id);
        criteriaQuery.where(idPredicate);

        TypedQuery<Users> query = entityManager.createQuery(criteriaQuery);
        System.out.println(query.getSingleResult().getId());

        return query.getSingleResult();
//        return Optional.empty();
    }
}
//@Autowired
//RabbitTemplate rabbitTemplate;
//@Autowired
//ProductRepository productRepository;
//@Autowired
//AMQPProducer amqpProducer;
//@Autowired
//RabbitMQListener rabbitMQListener;
//@Autowired
//RabbitMQProperties rabbitMQProperties;
//@Autowired
//EmailSenderService sendMail;
//@Autowired
//    ProductVariationRepository productVariationRepository;
//@Autowired
//    CustomerRepository customerRepository;
//@Autowired
//    AddressRepository addressRepository;
//
//
//public void placeOrder(Long productVariationId, int quantity, String paymentMethod, Long addressId) {
//    String email = currentUserService.getUser();
//    Customer customer = customerRepository.findByEmail(email);
//    Optional<ProductVariation> productVariationOptional = productVariationRepository.findById(productVariationId);
//    ProductVariation productVariation = productVariationOptional.get();
//    if (productVariation.getActive() == false) {
//        throw new NullPointerException("this item is not available");
//    }
//    Long quantity_Available = productVariation.getQuantityAvailable();
//    Long quantityAvailable = quantity_Available - quantity;
//    if (quantityAvailable < 0) {
//        throw new NullPointerException("only " + quantity_Available + " are in stock please select in this range");
//    }
//    productVariation.setQuantityAvailable(quantityAvailable);
//    productVariationRepository.save(productVariation);
//
//    Optional<Address> addressOptional = addressRepository.findById(addressId);
//    Address address = null;
//    if (addressOptional.isPresent()) {
//        address = addressOptional.get();
//    } else {
//        throw new NullPointerException("add an address to place order");
//    }
//    Orders orders = new Orders();
//    orders.setAmountPaid((productVariationRepository.getPrice(productVariationId)) * quantity);
//    orders.setCustomerName(customer);
//    orders.setPaymentMethod(paymentMethod);
//    orders.setOrderAddress(addressToOrderAddress(address));
//
//    OrderProduct orderProduct = new OrderProduct();
//    orderProduct.setPrice((productVariationRepository.getPrice(productVariationId)) * quantity);
//    orderProduct.setOrders(orders);
//    orderProduct.setProductVariation(productVariation.getInfoJson());
//    orderProduct.setProductVariation(productVariation);
//
//    OrderStatus orderStatus = new OrderStatus();
//    orderStatus.setTransition_notes_comments(Status.ORDER_PLACED);
//    orderStatus.setOrderProduct(orderProduct);
//    Optional<Product> productOptional = productRepository.findById(productVariationRepository.getProductId(productVariationId));
//    Product product = productOptional.get();
//
//    System.out.println(("*****sending message*****"));
//    Notification msg = new Notification("to confirm order", product);
//    amqpProducer.sendMessage(msg);
//    System.out.println("====message send====");
//
//    ordersRepository.save(orders);
//}