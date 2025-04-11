package com.chronodebileplus.store.product;

import java.util.Objects;

public class ProductStockDto {

    private Long productId;
    private String productName;
    private Long quantity;

    public ProductStockDto() {}

    public ProductStockDto(Long productId, String productName, Long quantity) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductStockDto that = (ProductStockDto) o;
        return Objects.equals(productId, that.productId) && Objects.equals(productName, that.productName) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, quantity);
    }

    @Override
    public String toString() {
        return "ProductStockDto{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
