package com.ReadIsGood.api.service.order;

import com.ReadIsGood.api.model.enity.order.OrderHeader;
import com.ReadIsGood.api.model.enity.order.OrderLine;
import com.ReadIsGood.api.model.enity.order.OrderLineDto;
import com.ReadIsGood.api.model.enity.order.Trade;
import com.ReadIsGood.api.model.enity.product.Book;
import com.ReadIsGood.api.model.repository.order.OrderLineRepository;
import com.ReadIsGood.api.service.product.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderLineService {

    @Autowired
    private final StockService stockService;
    @Autowired
    private final OrderLineRepository orderLineRepository;
    @Autowired
    private final BookService bookService;

    public OrderLineService(StockService stockService, OrderLineRepository orderLineRepository, BookService bookService) {
        this.stockService = stockService;
        this.orderLineRepository = orderLineRepository;
        this.bookService = bookService;
    }

    public void setOrderLinesToStock(List<OrderLine> orderLines){
        orderLines.forEach(orderLine -> {
            stockService.updateStock(new Trade(orderLine));
        });
    }

    public void addOrderLinesToHeader(List<OrderLine> orderLines, OrderHeader orderHeader){
        orderLines.forEach(orderLine -> orderLine.setOrderHeader(orderHeader));
    }

    public List<OrderLineDto> getDetails(Long id){
        List<OrderLine> orderLines =  orderLineRepository.findAllByOrderHeader(id);
        return orderLines.stream().map(this::convertOrderLine).collect(Collectors.toList());
    }

    private OrderLineDto convertOrderLine(OrderLine orderLine){
        OrderLineDto orderLineDto = new OrderLineDto(orderLine);
        Book book = bookService.getBookById(orderLineDto.getProductId());
        orderLineDto.setName(book.getName());
        orderLineDto.setAmount(book.getAmount().multiply(BigDecimal.valueOf(orderLine.getQuantity())));
        return orderLineDto;
    }

    public void saveAllOrderLines(List<OrderLine> orderLines){
        orderLineRepository.saveAll(orderLines);
    }
}
