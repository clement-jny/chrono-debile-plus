package com.chronodebileplus.chrono.store.stock;

import com.chronodebileplus.chrono.store.product.ProductStockDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

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
}
