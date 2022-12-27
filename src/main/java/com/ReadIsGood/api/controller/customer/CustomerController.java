package com.ReadIsGood.api.controller.customer;

import com.ReadIsGood.api.model.enity.customer.Customer;
import com.ReadIsGood.api.model.enity.customer.CustomerDto;
import com.ReadIsGood.api.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getCustomerInfo")
    public ResponseEntity<?> getCustomerInfo(Authentication token){
        return ResponseEntity.ok().body(new CustomerDto(customerService.getCustomerInfo(token.getName())));
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer){
        try {
            customerService.createNewCustomer(customer);
            return ResponseEntity.ok().body(new CustomerDto(customer));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<?> getAllOrders(Authentication token,Pageable pageable){
        Customer customer = customerService.getCustomerInfo(token.getName());
        return ResponseEntity.ok().body(customerService.getAllOrders(customer.getId(),pageable));
    }

}
