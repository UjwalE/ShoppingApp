package com.springboot.shoppingapp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int prodId;

    String productName;

    float price;

    /*@ManyToOne(cascade = CascadeType.DETACH)
    ProductCatalog catalog;*/

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    /*public ProductCatalog getCatalog() {
        return catalog;
    }

    public void setCatalog(ProductCatalog catalog) {
        this.catalog = catalog;
    }*/

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getProdId() == product.getProdId() &&
                Float.compare(product.getPrice(), getPrice()) == 0 &&
                getProductName().equals(product.getProductName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProdId(), getProductName(), getPrice());
    }
}
