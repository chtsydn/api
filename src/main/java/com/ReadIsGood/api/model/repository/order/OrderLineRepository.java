package com.ReadIsGood.api.model.repository.order;

import com.ReadIsGood.api.model.enity.order.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {

}
