package com.ReadIsGood.api.service.customer;

import com.ReadIsGood.api.model.enity.customer.Customer;
import com.ReadIsGood.api.model.enity.customer.CustomerLogin;
import com.ReadIsGood.api.model.enity.order.OrderHeader;
import com.ReadIsGood.api.model.repository.customer.CustomerRepository;
import com.ReadIsGood.api.security.LoginRequest;
import com.ReadIsGood.api.service.order.OrderHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final OrderHeaderService orderHeaderService;

    public CustomerService(CustomerRepository customerRepository, OrderHeaderService orderHeaderService) {
        this.customerRepository = customerRepository;
        this.orderHeaderService = orderHeaderService;
    }

    public Customer getCustomerInfo(String email){
        Optional<Customer> customer = customerRepository.findCustomerByEmail(email);
        return customer.orElse(null);
    }

    public void createNewCustomer(Customer customer){
        Optional<Customer> customerByEmail = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerByEmail.isPresent()){
            throw new IllegalArgumentException("Existing email");
        }
        String password = customer.getPassword();
        String salt = BCrypt.gensalt();
        String encodedPassword = BCrypt.hashpw(password,salt);
        customer.setPassword(encodedPassword);
        customer.setSalt(salt);
        customerRepository.save(customer);
    }

    public Boolean login(LoginRequest loginRequest){
        Optional<CustomerLogin> customerLoginOptional = customerRepository.findByEmail(loginRequest.getEmail());
        if (customerLoginOptional.isPresent()){
            CustomerLogin customerLogin = customerLoginOptional.get();
            String salt = customerLogin.getSalt();
            String encodedPassword = BCrypt.hashpw(loginRequest.getPassword(),salt);
            return encodedPassword.equals(customerLogin.getPassword());
        }
        return false;
    }

    public Optional<CustomerLogin> getCustomerLogin(String email){
        return customerRepository.findByEmail(email);
    }

    public Page<OrderHeader> getAllOrders(Customer customer, Pageable pageable){
        return orderHeaderService.getAllOrders(customer,pageable);
    }
}
