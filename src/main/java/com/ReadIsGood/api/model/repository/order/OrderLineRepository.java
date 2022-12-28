package com.ReadIsGood.api.model.repository.order;

import com.ReadIsGood.api.model.enity.order.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {
    @Query(value = "select * from order_line o where o.order_header_id = ?1", nativeQuery = true)
    List<OrderLine> findAllByOrderHeader(Long id);
}
