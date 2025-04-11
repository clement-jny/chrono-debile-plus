package com.chronodebileplus.store.orders;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class OrderPaymentDto {

    @JsonProperty("totalAmount")
    private Double totalAmount;

    public OrderPaymentDto(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderPaymentDto paymentDto = (OrderPaymentDto) o;
        return Objects.equals(totalAmount, paymentDto.totalAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(totalAmount);
    }

    @Override
    public String toString() {
        return "OrderPaymentDto{" + "totalAmount=" + totalAmount + '}';
    }
}
