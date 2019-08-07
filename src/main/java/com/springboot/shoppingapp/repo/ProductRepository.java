package com.springboot.shoppingapp.repo;

import com.springboot.shoppingapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends ProdBaseRepository<Product> {

    List findByProductNameContains(String s);
}
