package com.chronodebileplus.store.stock;

import com.chronodebileplus.store.product.ProductStockDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class StockServiceUnitTest {
    private final StockService stockService;
    private final StockDto baseStockDto;
    private static final Logger logger = LoggerFactory.getLogger(StockServiceUnitTest.class);

    @Autowired
    public StockServiceUnitTest(StockService _stockService) {
        this.stockService = _stockService;

        this.baseStockDto = new StockDto(
            List.of(
                new ProductStockDto(1L, "Pdt1", 5L),
                new ProductStockDto(2L, "Pdt2", 78L)
            )
        );
        this.stockService.save(this.baseStockDto);
    }

    @Test
    public void shouldGetStock() {
        // Given
        logger.info("shouldGetStock::baseStockDto: {}", this.baseStockDto.getProducts().toString());

        // When
        StockDto result = this.stockService.get();
        logger.info("shouldGetStock::result: {}", result.getProducts().toString());

        // Then
        Assertions.assertThat(result.getProducts()).isEqualTo(this.baseStockDto.getProducts());
    }

    @Test
    @DirtiesContext
    public void shouldSetStock() {
        // Given
        StockDto newStockDto = new StockDto(
            List.of(
                new ProductStockDto(3L, "Pdt3", 10L)
            )
        );
        logger.info("shouldSetStock::newStockDto: {}", newStockDto.getProducts().toString());

        //StockDto oldStockDto = this.stockService.get();

        // When
        StockDto result = this.stockService.save(newStockDto);
        logger.info("shouldSetStock::result: {}", result.getProducts().toString());

        //this.stockDto.setProducts(this.stockDto.getProducts(), newStockDto.getProducts());

        // Then
        //logger.info(result.getProducts().toString());
        Assertions.assertThat(result.getProducts()).isEqualTo(newStockDto.getProducts());


        StockDto currentStockDto = this.stockService.get();
        logger.info("shouldSetStock::currentStockDto: {}", currentStockDto.getProducts().toString());

        // Merge newStockDto into baseStockDto
        List<ProductStockDto> mergedProducts = this.baseStockDto.getProducts().stream()
            .filter(baseProduct -> newStockDto.getProducts().stream()
            .noneMatch(newProduct -> newProduct.getProductId().equals(baseProduct.getProductId())))
            .collect(Collectors.toList());

        mergedProducts.addAll(newStockDto.getProducts());

        StockDto expectedStockDto = new StockDto(mergedProducts);
        logger.info("shouldSetStock::expectedStockDto: {}", expectedStockDto.getProducts().toString());

        // Verify the current stock matches our expected merged result
        Assertions.assertThat(currentStockDto.getProducts()).containsExactlyInAnyOrderElementsOf(expectedStockDto.getProducts());
    }
}
