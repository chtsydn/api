package com.ReadIsGood.api.controller.order;

import com.ReadIsGood.api.model.enity.order.OrderHeader;
import com.ReadIsGood.api.model.enity.order.OrderLine;
import com.ReadIsGood.api.service.order.OrderHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

     @Autowired
     private final OrderHeaderService orderHeaderService;

    public OrderController(OrderHeaderService orderHeaderService) {
        this.orderHeaderService = orderHeaderService;
    }

    @RequestMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestParam OrderHeader orderHeader, @RequestParam List<OrderLine> orderLines){
        try {
            orderHeader.setOrderLines(orderLines);
            orderHeaderService.createOrder(orderHeader);
            return ResponseEntity.ok().body("Order success.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Order failed.");
        }
    }
}
