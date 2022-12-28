package com.ReadIsGood.api.model.enity.order;

import com.ReadIsGood.api.model.enity.product.ProductType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderLineDto {
    private Long id;
    private String name;
    private ProductType productType;
    private Long productId;
    private BigDecimal amount;
    private Integer quantity;

    public OrderLineDto(OrderLine orderLine){
        this.id=orderLine.getId();
        this.productType=orderLine.getProductType();
        this.productId=orderLine.getProductId();
        this.quantity=orderLine.getQuantity();
    }
}
