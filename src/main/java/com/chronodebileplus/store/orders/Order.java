package com.chronodebileplus.store.orders;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import java.util.Objects;

@Entity(name = "order_product")
public class Order {

    @EmbeddedId
    private OrderKey orderKey;

    @Column
    private Long quantity;

    public Order() {}

    public Order(Long id, Long productId, Long quantity) {
        super();
        this.orderKey = new OrderKey(id, productId);
        this.quantity = quantity;
    }

    public Long getId() {
        return orderKey.getId();
    }

    public void setId(Long id) {
        this.orderKey.setId(id);
    }

    public Long getProductId() {
        return orderKey.getProductId();
    }

    public void setProductId(Long productId) {
        this.orderKey.setProductId(productId);
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
        Order product = (Order) o;
        return (
            Objects.equals(orderKey.getId(), product.orderKey.getId()) &&
            Objects.equals(
                orderKey.getProductId(),
                product.orderKey.getProductId()
            ) &&
            Objects.equals(quantity, product.quantity)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            orderKey.getId(),
            orderKey.getProductId(),
            quantity
        );
    }

    @Override
    public String toString() {
        return (
            "Product{" +
            "id=" +
            orderKey.getId() +
            ", productId=" +
            orderKey.getProductId() +
            ", quantity=" +
            quantity +
            '}'
        );
    }
}
