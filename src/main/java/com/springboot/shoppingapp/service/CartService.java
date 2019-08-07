package com.springboot.shoppingapp.service;

import com.springboot.shoppingapp.dto.CartDto;
import com.springboot.shoppingapp.dto.ProductDto;

public interface CartService {

    CartDto addToCart(Integer id, ProductDto productDto);

    CartDto removeFromCart(Integer integer, int productId);

    CartDto updateCart(Integer integer, ProductDto productDto);
}
