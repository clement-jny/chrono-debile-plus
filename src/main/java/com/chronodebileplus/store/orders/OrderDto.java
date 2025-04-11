package com.chronodebileplus.store.orders;

import com.chronodebileplus.store.product.ProductOrderDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class OrderDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("items")
    private List<ProductOrderDto> products;

    public OrderDto(Long id, List<ProductOrderDto> products) {
        this.id = id;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductOrderDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductOrderDto> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto stockDto = (OrderDto) o;
        return Objects.equals(products, stockDto.products);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(products);
    }

    @Override
    public String toString() {
        return "StockDto{" + "products=" + products + '}';
    }
}
