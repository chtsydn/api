package com.ReadIsGood.api.service.order;

import com.ReadIsGood.api.model.enity.order.Trade;
import com.ReadIsGood.api.model.enity.order.TradeType;
import com.ReadIsGood.api.model.enity.order.Stock;
import com.ReadIsGood.api.model.enity.product.ProductType;
import com.ReadIsGood.api.model.repository.order.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock updateStock(Trade trade){
        Optional<Stock> existingStock = stockRepository.findStockByProductIdAndProductType(trade.getProductId(),trade.getProductType());
        if (existingStock.isPresent()){
            Stock stock = existingStock.get();
            Integer quantity = stock.getQuantity();
            if (trade.getTradeType().equals(TradeType.IN)){
                stock.setQuantity(quantity+trade.getQuantity());
            }else if (quantity>=trade.getQuantity()){
                stock.setQuantity(quantity-trade.getQuantity());
            }else {
                throw new IllegalArgumentException("The requested quantity is not in stock.");
            }
            return stockRepository.save(stock);
        }else {
            if (trade.getTradeType().equals(TradeType.OUT)){
                throw new IllegalArgumentException("The product is out of stock.");
            }
            Stock newStock = new Stock();
            newStock.setProductId(trade.getProductId());
            newStock.setProductType(trade.getProductType());
            newStock.setQuantity(trade.getQuantity());
            return stockRepository.save(newStock);
        }
    }

    public Stock getStock(Long id, ProductType productType){
        Optional<Stock> stock = stockRepository.findStockByProductIdAndProductType(id,productType);
        if (stock.isEmpty())throw new IllegalArgumentException("No stock for this product.");
        return stock.get();
    }
}
