package com.ReadIsGood.api.model.enity.order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDto{
    private Long id;
    private Long customerId;
    private Date orderDate;
    private String orderNote;
    private BigDecimal orderAmount;

    public OrderDto(OrderHeader orderHeader){
        this.id= orderHeader.getId();
        this.customerId=orderHeader.getCustomer_id();
        this.orderDate=orderHeader.getOrder_time();
        this.orderNote=orderHeader.getOrder_note();
        this.orderAmount=orderHeader.getOrder_amount();
    }
}
