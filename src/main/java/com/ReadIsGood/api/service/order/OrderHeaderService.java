package com.ReadIsGood.api.service.order;

import com.ReadIsGood.api.model.enity.order.OrderDto;
import com.ReadIsGood.api.model.enity.order.OrderHeader;
import com.ReadIsGood.api.model.repository.order.OrderHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderHeaderService {
    @Autowired
    private final OrderHeaderRepository orderHeaderRepository;
    @Autowired
    private final OrderLineService orderLineService;

    public OrderHeaderService(OrderHeaderRepository orderHeaderRepository, OrderLineService orderLineService) {
        this.orderHeaderRepository = orderHeaderRepository;
        this.orderLineService = orderLineService;
    }

    public void createOrder(OrderHeader orderHeader){
        orderLineService.setOrderLinesToStock(orderHeader.getOrderLines());
        orderHeaderRepository.save(orderHeader);
        orderLineService.addOrderLinesToHeader(orderHeader.getOrderLines(),orderHeader);
        orderLineService.saveAllOrderLines(orderHeader.getOrderLines());
    }

    public List<OrderDto> getAllOrders(Long customer, Pageable pageable){
        List<OrderHeader> orderHeaders = orderHeaderRepository.findAllByCustomerId(customer,pageable);
        return orderHeaders.stream().map(OrderDto::new).collect(Collectors.toList());
    }

    public List<OrderDto> getAllOrdersByDate(Long customerId, Date firstDate,Date lastDate,Pageable pageable){
        List<OrderHeader> orderHeaders = orderHeaderRepository.findAllByCustomerIdWithDateInterval(customerId,firstDate,lastDate,pageable);
        return orderHeaders.stream().map(OrderDto::new).collect(Collectors.toList());
    }

    public OrderDto getOrderById(Long orderId){
        Optional<OrderHeader> orderHeader = orderHeaderRepository.findById(orderId);
        if (orderHeader.isPresent())
            return new OrderDto(orderHeader.get());
        throw new IllegalArgumentException("Incorrect order id.");
    }
}
