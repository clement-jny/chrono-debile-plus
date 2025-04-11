package com.chronodebileplus.store.orders;

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
@RequestMapping("/store/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(
        OrderController.class
    );

    @Autowired
    OrderService service;

    @Operation(
        description = "Places a new order, verifying the available quantities."
    )
    @PostMapping(produces = "application/json")
    public OrderDto save(
        @Parameter(
            description = "List of the products. Notice that, if an id already exists, this will alter the existing product."
        ) @RequestBody OrderDto orderDto
    ) {
        return service.save(orderDto);
    }
}
