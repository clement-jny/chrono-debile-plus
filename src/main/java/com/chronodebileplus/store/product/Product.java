package com.chronodebileplus.store.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    private Long productId;

    private String productName;

    private Long quantity;

    private double amount;

    public Product() {}

    public Product(
        Long productId,
        String productName,
        Long quantity,
        double amount
    ) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.amount = amount;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("Product [id=")
            .append(productId)
            .append(", productName=")
            .append(productName)
            .append(", quantity=")
            .append(quantity)
            .append(", amount=")
            .append(amount)
            .append("]");
        return builder.toString();
    }
}
