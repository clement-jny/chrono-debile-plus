package com.chronodebileplus.chrono.store.stock;

import com.chronodebileplus.chrono.store.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    ProductRepository productRepository;

    public StockDto save(StockDto dto) {
        var products = productRepository.saveAll(dto.getProducts());

        return new StockDto(products);
    }
}
