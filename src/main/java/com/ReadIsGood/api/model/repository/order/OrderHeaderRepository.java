package com.ReadIsGood.api.model.repository.order;

import com.ReadIsGood.api.model.enity.order.OrderHeader;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderHeaderRepository extends JpaRepository<OrderHeader,Long> {
    @Query("select o from OrderHeader o where o.customer_id = ?1")
    List<OrderHeader> findAllByCustomer_id(Long customer, Pageable pageable);
}
