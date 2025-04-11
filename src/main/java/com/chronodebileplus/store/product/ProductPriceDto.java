package com.chronodebileplus.store.product;

import java.util.Objects;

public class ProductPriceDto {
    private Long productId;
    private double amount;

    public ProductPriceDto() {}

    public ProductPriceDto(Long productId, double amount) {
        this.productId = productId;
        this.amount = amount;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductPriceDto that = (ProductPriceDto) o;
        return Double.compare(amount, that.amount) == 0 && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, amount);
    }

    @Override
    public String toString() {
        return "ProductPriceDto{" +
                "productId=" + productId +
                ", amount=" + amount +
                '}';
    }
}
