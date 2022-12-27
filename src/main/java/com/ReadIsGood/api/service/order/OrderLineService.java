package com.ReadIsGood.api.service.order;

import com.ReadIsGood.api.model.enity.order.OrderHeader;
import com.ReadIsGood.api.model.enity.order.OrderLine;
import com.ReadIsGood.api.model.enity.order.Trade;
import com.ReadIsGood.api.model.repository.order.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineService {

    @Autowired
    private final StockService stockService;
    @Autowired
    private final OrderLineRepository orderLineRepository;

    public OrderLineService(StockService stockService, OrderLineRepository orderLineRepository) {
        this.stockService = stockService;
        this.orderLineRepository = orderLineRepository;
    }

    public void setOrderLinesToStock(List<OrderLine> orderLines){
        orderLines.forEach(orderLine -> {
            stockService.updateStock(new Trade(orderLine));
        });
    }

    public void addOrderLinesToHeader(List<OrderLine> orderLines, OrderHeader orderHeader){
        orderLines.forEach(orderLine -> orderLine.setOrderHeader(orderHeader));
    }
    public void saveAllOrderLines(List<OrderLine> orderLines){
        orderLineRepository.saveAll(orderLines);
    }
}
