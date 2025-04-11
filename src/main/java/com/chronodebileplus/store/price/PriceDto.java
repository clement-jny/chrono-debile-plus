package com.chronodebileplus.store.price;

import com.chronodebileplus.store.product.ProductPriceDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class PriceDto {
    @JsonProperty("prices")
    private List<ProductPriceDto> prices;

    public PriceDto(List<ProductPriceDto> prices) {
        this.prices = prices;
    }

    public List<ProductPriceDto> getPrices() {
        return prices;
    }

    public void setPrices(List<ProductPriceDto> prices) {
        this.prices = prices;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PriceDto priceDto = (PriceDto) o;
        return Objects.equals(prices, priceDto.prices);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(prices);
    }

    @Override
    public String toString() {
        return "PriceDto{" +
                "prices=" + prices +
                '}';
    }
}
