package com.ReadIsGood.api.model.enity.order;

import com.ReadIsGood.api.model.enity.product.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Trade {
    private TradeType tradeType;
    private Long productId;
    private ProductType productType;
    private Integer quantity;

    public Trade(OrderLine orderLine){
        this.tradeType=TradeType.OUT;
        this.productId=orderLine.getProductId();
        this.productType=orderLine.getProductType();
        this.quantity=orderLine.getQuantity();
    }
}
