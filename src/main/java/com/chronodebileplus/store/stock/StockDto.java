package com.chronodebileplus.store.stock;

import com.chronodebileplus.store.product.ProductStockDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class StockDto {

    @JsonProperty("stock")
    private List<ProductStockDto> products;

    public StockDto(List<ProductStockDto> products) {
        this.products = products;
    }

    public List<ProductStockDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductStockDto> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StockDto stockDto = (StockDto) o;
        return Objects.equals(products, stockDto.products);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(products);
    }

    @Override
    public String toString() {
        return "StockDto{" +
                "products=" + products +
                '}';
    }
}
