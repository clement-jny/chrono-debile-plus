package com.chronodebileplus.store.price;

import com.chronodebileplus.store.stock.StockController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store/prices")
public class PriceController {
    private static final Logger logger = LoggerFactory.getLogger(PriceController.class);

    @Autowired
    PriceService priceService;

    @Operation(description = "Get the prices of products")
    @GetMapping(produces = "application/json")
    public PriceDto get() {
        logger.info("Get prices");
        return this.priceService.get();
    }

    @Operation(description = "Set the prices of products")
    @PostMapping(produces = "application/json")
    public PriceDto save(@Parameter(description = "List of ") @RequestBody PriceDto priceDto) {
        return this.priceService.save(priceDto);
    }
}
