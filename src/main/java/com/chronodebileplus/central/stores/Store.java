package com.chronodebileplus.central.stores;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Store {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    // @JsonProperty("baseUrl")
    private String baseUrl;

    // Constructeur vide pour la désérialisation JSON
    public Store() {}

    // Constructeur pour création d'un nouveau store (sans id)
    public Store(String name, String baseUrl) {
        this.name = name;
        this.baseUrl = baseUrl;
    }

    // Constructeur pour récupération via l'API avec id (sans baseUrl)
    public Store(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }
    // public void setId(Long id) {
    //     this.id = id;
    // }

    public String getName() {
        return name;
    }
    // public void setName(String name) {
    //     this.name = name;
    // }

    public String getBaseUrl() {
        return baseUrl;
    }
    // public void setBaseUrl(String baseUrl) {
    //     this.baseUrl = baseUrl;
    // }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(id, store.id) && Objects.equals(name, store.name) && Objects.equals(baseUrl, store.baseUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, baseUrl);
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                '}';
    }
}
