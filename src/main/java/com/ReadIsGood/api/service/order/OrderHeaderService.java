package com.ReadIsGood.api.service.order;

import com.ReadIsGood.api.model.enity.customer.Customer;
import com.ReadIsGood.api.model.enity.order.OrderHeader;
import com.ReadIsGood.api.model.repository.order.OrderHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderHeaderService {
    @Autowired
    private final OrderHeaderRepository orderHeaderRepository;

    public OrderHeaderService(OrderHeaderRepository orderHeaderRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
    }

    public void createOrder(OrderHeader orderHeader){
        orderHeaderRepository.save(orderHeader);
    }

    public Page<OrderHeader> getAllOrders(Customer customer, Pageable pageable){
        return orderHeaderRepository.findAllByCustomer(customer,pageable);
    }
}
