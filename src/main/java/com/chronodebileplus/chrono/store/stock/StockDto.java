package com.chronodebileplus.chrono.store.stock;

import com.chronodebileplus.chrono.store.product.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class StockDto {

    @JsonProperty("stock")
    private List<Product> products;

    public StockDto(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
