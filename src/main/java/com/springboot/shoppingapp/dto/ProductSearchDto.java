package com.springboot.shoppingapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ProductSearchDto {

    int id;
    String name;
    float price;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String brand;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String design;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String genre;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String author;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String publication;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }
}
