package com.springboot.shoppingapp.service;

import com.springboot.shoppingapp.dto.CartDto;
import com.springboot.shoppingapp.dto.UserDto;
import com.springboot.shoppingapp.entity.User;

import java.util.List;

public interface UserService {
    List getAll();

    UserDto findById(Integer integer);

    int add(UserDto user);

    CartDto viewCart(Integer integer);
}
