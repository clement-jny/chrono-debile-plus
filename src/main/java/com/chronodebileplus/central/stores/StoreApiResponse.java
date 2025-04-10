package com.chronodebileplus.central.stores;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StoreApiResponse {
    // PROPERTIES
    @JsonProperty("stores")
    private List<Store> stores;


    // GETTER / SETTER
    public List<Store> getStores() {
        // TODO: check if correct
        // if (stores == null) {
        //     throw new IllegalStateException("Stores list is not initialized");
        // }
        // if (stores.isEmpty()) {
        //     throw new IllegalStateException("Stores list is empty");
        // }
        return this.stores.stream()
            .map(storeResponse -> new Store(storeResponse.getId(), storeResponse.getName()))
            .collect(Collectors.toList());
    }
    public void setStores(List<Store> stores) {
        this.stores = stores;
    }


    // OVERRIDE
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StoreApiResponse that = (StoreApiResponse) o;
        return Objects.equals(stores, that.stores);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(stores);
    }

    @Override
    public String toString() {
        return "StoreApiResponse{" +
                "stores=" + stores +
                '}';
    }
}