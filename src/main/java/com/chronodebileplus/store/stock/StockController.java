package com.chronodebileplus.store.stock;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store/stock")
public class StockController {

    private static final Logger logger = LoggerFactory.getLogger(
        StockController.class
    );

    @Autowired
    StockService service;

    @Operation(description = "Get the stock of products")
    @GetMapping(produces = "application/json")
    public StockDto get() {
        return service.get();
    }

    @Operation(description = "Set the stock of products")
    @PostMapping(produces = "application/json")
    public StockDto save(
        @Parameter(
            description = "List of the products. Notice that, if an id already exists, this will alter the existing product."
        ) @RequestBody StockDto stockDto
    ) {
        return service.save(stockDto);
    }
}
