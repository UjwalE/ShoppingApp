package com.springboot.shoppingapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int cartId;

    @JsonManagedReference
    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
    User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Catalog_Prod_Id")
    List<ProductCatalog> productCatalog = new ArrayList<>();

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ProductCatalog> getProductCatalog() {
        return productCatalog;
    }

    public void setProductCatalog(List<ProductCatalog> productCatalog) {
        this.productCatalog = productCatalog;
    }
}
