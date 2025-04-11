package com.chronodebileplus.store.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class Product {

    @Id
    private Long productId;

    private String productName;

    private Long quantity;

    private double amount;

    public Product() {}

    public Product(Long productId) {
        this.productId = productId;
    }

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

    public Product deepCopy() {
        return new Product(
            this.productId,
            this.productName,
            this.quantity,
            this.amount
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return (
            Double.compare(amount, product.amount) == 0 &&
            Objects.equals(productId, product.productId) &&
            Objects.equals(productName, product.productName) &&
            Objects.equals(quantity, product.quantity)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, quantity, amount);
    }

    @Override
    public String toString() {
        return (
            "Product{" +
            "productId=" +
            productId +
            ", productName='" +
            productName +
            '\'' +
            ", quantity=" +
            quantity +
            ", amount=" +
            amount +
            '}'
        );
    }
}
