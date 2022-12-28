package com.ReadIsGood.api.controller.order;

import com.ReadIsGood.api.model.enity.order.OrderHeader;
import com.ReadIsGood.api.model.enity.order.OrderLine;
import com.ReadIsGood.api.service.order.OrderHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public ResponseEntity<?> createOrder(@RequestBody OrderHeader orderHeader){
        try {
            orderHeader.setOrder_time(new Date());
            orderHeaderService.createOrder(orderHeader);
            return ResponseEntity.ok().body("Order success.");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Order failed.");
        }
    }

    @GetMapping("/getOrder")
    public ResponseEntity<?> getOrderById(@RequestParam("id") Long id){
        return ResponseEntity.ok().body(orderHeaderService.getOrderById(id));
    }
}
