package com.ReadIsGood.api.model.repository.order;

import com.ReadIsGood.api.model.enity.customer.Customer;
import com.ReadIsGood.api.model.enity.order.OrderHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHeaderRepository extends JpaRepository<OrderHeader,Long> {
    Page<OrderHeader> findAllByCustomer(Customer customer, Pageable pageable);
}
