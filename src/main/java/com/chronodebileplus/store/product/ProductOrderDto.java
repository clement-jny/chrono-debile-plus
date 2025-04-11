package com.chronodebileplus.store.product;

import java.util.Objects;

public class ProductOrderDto {

    private Long productId;
    private Long quantity;

    public ProductOrderDto() {}

    public ProductOrderDto(Long productId, Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
        ProductOrderDto that = (ProductOrderDto) o;
        return (
            Objects.equals(productId, that.productId) &&
            Objects.equals(quantity, that.quantity)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity);
    }

    @Override
    public String toString() {
        return (
            "ProductOrderDto{" +
            "productId=" +
            productId +
            ", quantity=" +
            quantity +
            '}'
        );
    }
}
