package com.springboot.shoppingapp.service;

import com.springboot.shoppingapp.dto.ProductSearchDto;

import java.util.List;

public interface ProductService {
    List getAll();

    List getByName(String name);

    ProductSearchDto getById(int id);
}
