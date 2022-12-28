package com.ReadIsGood.api.model.repository.order;

import com.ReadIsGood.api.model.enity.order.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {
    @Query("select o from OrderLine o where o.orderHeader = ?1")
    List<OrderLine> findAllByOrderHeader(Long id);
}
