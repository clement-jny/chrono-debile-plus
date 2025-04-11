package com.chronodebileplus.store.stock;

import com.chronodebileplus.central.stores.StoreInitializer;
import com.chronodebileplus.store.product.ProductStockDto;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
public class StockServiceUnitTest {
    private final StockService stockService;
    private final StockDto stockDto;
    private static final Logger logger = LoggerFactory.getLogger(StockServiceUnitTest.class);

    @Autowired
    public StockServiceUnitTest(StockService _stockService) {
        this.stockService = _stockService;

        this.stockDto = new StockDto(
            List.of(
                new ProductStockDto(1L, "Pdt1", 5L),
                new ProductStockDto(2L, "Pdt2", 78L)
            )
        );
    }

    @BeforeEach
    public void setup() {
        this.stockService.save(stockDto);
    }

    @Test
    public void shouldGetStock() {
        // Given
        logger.info("given::shouldGetStock: " + this.stockDto.getProducts().toString());

        // When
        StockDto result = this.stockService.get();
        logger.info("result::shouldGetStock: " + result.getProducts().toString());

        // Then
        Assertions.assertThat(result.getProducts()).isEqualTo(this.stockDto.getProducts());
    }

    @Test
    public void shouldSetStock() {
        // Given
        StockDto newStockDto = new StockDto(
            List.of(
                new ProductStockDto(1L, "Pdt3", 10L)
            )
        );
        logger.info("given::shouldSetStock: " + newStockDto.getProducts().toString());

        //StockDto oldStockDto = this.stockService.get();



        // When
        StockDto result = this.stockService.save(newStockDto);
        logger.info("result::shouldSetStock: " + result.getProducts().toString());

        //this.stockDto.setProducts(this.stockDto.getProducts(), newStockDto.getProducts());

        // Then
        //logger.info(result.getProducts().toString());
        Assertions.assertThat(result.getProducts()).isEqualTo(newStockDto.getProducts());


        StockDto currentStockDto = this.stockService.get();
        logger.info("then::shouldSetStock: " + currentStockDto.getProducts().toString());
        Assertions.assertThat(currentStockDto.getProducts()).isEqualTo(newStockDto.getProducts());
        //Assertions.assertThat(oldStockDto.getProducts()).isNotEqualTo(newStockDto.getProducts());
    }
}
