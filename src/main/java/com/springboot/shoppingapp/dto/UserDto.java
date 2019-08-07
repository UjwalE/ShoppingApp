package com.springboot.shoppingapp.dto;

import com.springboot.shoppingapp.entity.Cart;

public class UserDto {
    int id;
    String name;
    int cart_id;

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

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    @Override
    public String toString() {
        return "id: " +this.id+"; name: "+this.name+"; cart_id"+this.cart_id;
    }
}
