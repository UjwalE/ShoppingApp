package com.springboot.shoppingapp.service.impl;

import com.springboot.shoppingapp.controller.ShoppingController;
import com.springboot.shoppingapp.dto.CartDto;
import com.springboot.shoppingapp.dto.DtoConverter;
import com.springboot.shoppingapp.dto.UserDto;
import com.springboot.shoppingapp.entity.Cart;
import com.springboot.shoppingapp.entity.User;
import com.springboot.shoppingapp.exception.UserServiceException;
import com.springboot.shoppingapp.repo.UserRepository;
import com.springboot.shoppingapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(ShoppingController.class);

    @Autowired
    UserRepository repo;

    @Override
    public List getAll() {
        logger.debug("getAll()");

        try{
            return repo.findAll();
        }
        catch(DataAccessException ex){
            logger.error("getAll()");
            throw new UserServiceException(ex.getMessage());
        }
    }

    @Override
    public UserDto findById(Integer integer) {
        logger.debug("findById("+integer.intValue()+")");
        try{
            List<User> list = repo.findUserByUserId(integer);
            UserDto userDto = DtoConverter.convert((User) (list != null && !list.isEmpty() ? list.get(0) : null));
            return userDto;
        }
        catch (DataAccessException ex){
            logger.error("findById()");
            throw new UserServiceException(ex.getMessage());
        }
    }

    @Override
    public int add(UserDto userDto) {
        logger.debug("add("+userDto.getName()+")");
        try{
            User user = new User();
            user.setUserName(userDto.getName());
            Cart cart = new Cart();
            user.setCart(cart);
            return repo.save(user).getUserId();
        }
        catch(DataAccessException ex){
            logger.error("add()");
            throw new UserServiceException(ex.getMessage());
        }
    }

    @Override
    public CartDto viewCart(Integer integer) {
        logger.debug("viewCart("+integer.intValue()+")");
        try{
            List<User> list = repo.findUserByUserId(integer);
            User user = (User) (list != null && !list.isEmpty() ? list.get(0) : null);
            return DtoConverter.convert((Cart) (user != null ? user.getCart() : null));
        }
        catch (DataAccessException ex){
            logger.error("viewCart()");
            throw new UserServiceException(ex.getMessage());
        }
    }

}
