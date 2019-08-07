package com.springboot.shoppingapp.entity;

import javax.persistence.Entity;

@Entity(name = "apparals")
public class Apparal extends Product{

    String type;

    String brand;

    String design;

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
}
