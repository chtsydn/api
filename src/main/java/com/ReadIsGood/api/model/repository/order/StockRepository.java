package com.ReadIsGood.api.model.repository.order;

import com.ReadIsGood.api.model.enity.product.ProductType;
import com.ReadIsGood.api.model.enity.order.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {
    Optional<Stock> findStockByProductIdAndProductType(Long id, ProductType productType);
}
