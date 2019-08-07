package com.springboot.shoppingapp.repo;

import com.springboot.shoppingapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ProdBaseRepository<T extends Product> extends JpaRepository<T, Integer> {
}
