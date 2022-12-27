package com.ReadIsGood.api.controller.order;

import com.ReadIsGood.api.model.enity.order.Trade;
import com.ReadIsGood.api.service.order.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/updateStock")
    public ResponseEntity<?> updateStock(@RequestBody Trade trade){
        try{
            if (trade.getQuantity()<=0)
                throw new IllegalArgumentException("The quantity cannot be less or equal than zero.");
            return ResponseEntity.ok().body(stockService.updateStock(trade));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
