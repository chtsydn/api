package com.ReadIsGood.api.model.enity.order;

import com.ReadIsGood.api.model.enity.product.ProductType;
import lombok.Data;

@Data
public class Trade {
    private TradeType tradeType;
    private Long productId;
    private ProductType productType;
    private Integer quantity;
}
