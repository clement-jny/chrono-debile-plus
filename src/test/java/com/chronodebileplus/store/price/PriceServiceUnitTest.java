package com.chronodebileplus.store.price;

import com.chronodebileplus.store.product.ProductPriceDto;
import com.chronodebileplus.store.product.ProductStockDto;
import com.chronodebileplus.store.stock.StockDto;
import com.chronodebileplus.store.stock.StockService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class PriceServiceUnitTest {
    private final StockService stockService;
    private final PriceService priceService;

    private final StockDto baseStockDto;
    private final PriceDto basePriceDto;

    private static final Logger logger = LoggerFactory.getLogger(PriceServiceUnitTest.class);

    @Autowired
    public PriceServiceUnitTest(StockService _stockService, PriceService _priceService) {
        this.stockService = _stockService;
        this.priceService = _priceService;

        this.baseStockDto = new StockDto(
            List.of(
                new ProductStockDto(1L, "Pdt1", 5L),
                new ProductStockDto(2L, "Pdt2", 78L)
            )
        );
        this.stockService.save(this.baseStockDto);

        this.basePriceDto = new PriceDto(
            List.of(
                new ProductPriceDto(1L, 45),
                new ProductPriceDto(2L, 100)
            )
        );
        this.priceService.save(this.basePriceDto);
    }

    @Test
    public void shouldGetPrice() {
        // Given
        logger.info("shouldGetPrice::basePriceDto: {}", this.basePriceDto.getPrices().toString());

        // When
        PriceDto result = this.priceService.get();
        logger.info("shouldGetPrice::result: {}", result.getPrices().toString());

        // Then
        Assertions.assertThat(result.getPrices()).isEqualTo(this.basePriceDto.getPrices());
    }

    @Test
    @DirtiesContext
    public void shouldSetPrice() {
        // Given
        StockDto newStockDto = new StockDto(
            List.of(
                new ProductStockDto(3L, "Pdt3", 1L),
                new ProductStockDto(4L, "Pdt4", 19L)
            )
        );
        this.stockService.save(newStockDto);
        logger.info("shouldSetStock::newStockDto: {}", newStockDto.getProducts().toString());

        PriceDto newPriceDto = new PriceDto(
            List.of(
                new ProductPriceDto(3L, 90),
                new ProductPriceDto(4L, 8)
            )
        );
        logger.info("shouldSetPrice::newPriceDto: {}", newPriceDto.getPrices().toString());

        // When
        PriceDto result = this.priceService.save(newPriceDto);
        logger.info("shouldSetPrice::result: {}", result.getPrices().toString());

        // Then
        Assertions.assertThat(result.getPrices()).isEqualTo(newPriceDto.getPrices());


        PriceDto currentPriceDto = this.priceService.get();
        logger.info("shouldSetPrice::currentPriceDto: {}", currentPriceDto.getPrices().toString());

        // Merge newPriceDto into basePriceDto
        List<ProductPriceDto> mergedPrices = this.basePriceDto.getPrices().stream()
            .filter(basePrice -> newPriceDto.getPrices().stream()
                    .noneMatch(newProductPrice -> newProductPrice.getProductId().equals(basePrice.getProductId())))
            .collect(Collectors.toList());

        mergedPrices.addAll(newPriceDto.getPrices());

        PriceDto expectedPriceDto = new PriceDto(mergedPrices);
        logger.info("shouldSetPrice::expectedPriceDto: {}", expectedPriceDto.getPrices().toString());

        Assertions.assertThat(currentPriceDto.getPrices()).isEqualTo(expectedPriceDto.getPrices());
    }
}
