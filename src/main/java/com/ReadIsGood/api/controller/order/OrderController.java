package com.ReadIsGood.api.controller.order;

import com.ReadIsGood.api.model.enity.customer.Customer;
import com.ReadIsGood.api.model.enity.order.OrderHeader;
import com.ReadIsGood.api.service.customer.CustomerService;
import com.ReadIsGood.api.service.order.OrderHeaderService;
import com.ReadIsGood.api.service.order.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/order")
public class OrderController {

     @Autowired
     private final OrderHeaderService orderHeaderService;
     @Autowired
     private final OrderLineService orderLineService;
     @Autowired
     private final CustomerService customerService;

    public OrderController(OrderHeaderService orderHeaderService, OrderLineService orderLineService, CustomerService customerService) {
        this.orderHeaderService = orderHeaderService;
        this.orderLineService = orderLineService;
        this.customerService = customerService;
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

    @GetMapping("/getOrderDetails")
    public ResponseEntity<?> getOrderDetails(@RequestParam("id") Long id){
        return ResponseEntity.ok().body(orderLineService.getDetails(id));
    }

    @PostMapping("/getOrdersByDate")
    public ResponseEntity<?> getOrdersByDate(@RequestBody String jsonString, Authentication token, Pageable pageable){
        Customer customer = customerService.getCustomerInfo(token.getName());
        Date firstDate = new Date();
        Date lastDate = new Date();

        return ResponseEntity.ok().body(orderHeaderService.getAllOrdersByDate(customer.getId(),firstDate,lastDate,pageable));
    }
}
