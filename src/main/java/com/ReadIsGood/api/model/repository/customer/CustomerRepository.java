package com.ReadIsGood.api.model.repository.customer;

import com.ReadIsGood.api.model.enity.customer.Customer;
import com.ReadIsGood.api.model.enity.customer.CustomerLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findCustomerByEmail(String email);
    @Query("select c.password as password, c.salt as salt from Customer c where c.email=:email")
    Optional<CustomerLogin> findByEmail(String email);
}
