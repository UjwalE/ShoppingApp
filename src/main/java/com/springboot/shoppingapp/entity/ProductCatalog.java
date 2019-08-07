package com.springboot.shoppingapp.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProductCatalog implements Serializable {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;*/

    @OneToOne
    @JoinColumn(name = "Cart_Id")
    Cart cart;

    @EmbeddedId
    @OneToOne
    @JoinColumn(name = "Product_Id")
    Product product;

    int quantity;

    public ProductCatalog(){
        //Default constructor
    }

    public ProductCatalog(Cart cart, Product product, int quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
