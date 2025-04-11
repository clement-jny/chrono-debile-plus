package com.chronodebileplus.store.orders;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
            description = "Content of the order"
        ) @RequestBody OrderDto orderDto
    ) {
        return service.save(orderDto);
    }

    @Operation(
        description = "Creates a payment for a placed order, providing the total amount of the payment"
    )
    @PostMapping(value = "/{id}/payments", produces = "application/json")
    public OrderPaymentDto pay(
        @Parameter(description = "ID of the order") @PathVariable Long id
    ) {
        return service.pay(id);
    }
}
