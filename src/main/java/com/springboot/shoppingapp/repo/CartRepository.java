package com.springboot.shoppingapp.repo;

import com.springboot.shoppingapp.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
