package com.springboot.shoppingapp.service.impl;

import com.springboot.shoppingapp.controller.ShoppingController;
import com.springboot.shoppingapp.dto.CartDto;
import com.springboot.shoppingapp.dto.DtoConverter;
import com.springboot.shoppingapp.dto.ProductDto;
import com.springboot.shoppingapp.entity.Cart;
import com.springboot.shoppingapp.entity.Product;
import com.springboot.shoppingapp.entity.ProductCatalog;
import com.springboot.shoppingapp.entity.User;
import com.springboot.shoppingapp.exception.CartServiceException;
import com.springboot.shoppingapp.repo.CartRepository;
import com.springboot.shoppingapp.repo.ProductRepository;
import com.springboot.shoppingapp.repo.UserRepository;
import com.springboot.shoppingapp.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class CartServiceImpl implements CartService {

    Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    ProductRepository prodRepo;

    @Autowired
    CartRepository cartRepo;

    @Autowired
    UserRepository userRepo;


    @Override
    public CartDto addToCart(Integer id, ProductDto productDto) {
        logger.debug("addToCart("+id.intValue()+":"+productDto.toString()+")");
        try{
            boolean newFlag = true;
            productDto.setQuantity(productDto.getQuantity()>0?productDto.getQuantity():1);
            Product prod = prodRepo.findById(productDto.getId()).orElse(null);
            User user = userRepo.findById(id).orElse(null);
            Cart cart = user != null && user.getCart() != null ? user.getCart() : null;
            CopyOnWriteArrayList<ProductCatalog> list = new CopyOnWriteArrayList<>();
            if (cart.getProductCatalog() != null && !cart.getProductCatalog().isEmpty())
                list = new CopyOnWriteArrayList<>(cart.getProductCatalog());
            Iterator<ProductCatalog> itr = list.iterator();
            while (itr.hasNext()) {
                ProductCatalog p = itr.next();
                if (p.getProduct().equals(prod)) {
                    p.setQuantity(p.getQuantity()+productDto.getQuantity());
                    newFlag = false;
                }
            }
            if (newFlag) list.add(new ProductCatalog(cart, prod, productDto.getQuantity()));
            cart.setProductCatalog(list);
            cart = cartRepo.save(cart);

            return DtoConverter.convert(cart);
        }
        catch (IndexOutOfBoundsException | NullPointerException | DataAccessException exp){
            logger.error("addToCart()");
            throw new CartServiceException(exp.getMessage());
        }
    }

    @Override
    public CartDto removeFromCart(Integer integer, int productId) {
        logger.debug("removeFromCart("+integer.intValue()+":"+productId+")");
        try{
            Product prod = prodRepo.findById(productId).orElse(null);
            User user = userRepo.findById(integer).orElse(null);
            Cart cart = user != null && user.getCart() != null ? user.getCart() : null;
            if (cart.getProductCatalog() != null && !cart.getProductCatalog().isEmpty()) {
                CopyOnWriteArrayList<ProductCatalog> list = new CopyOnWriteArrayList<>(cart.getProductCatalog());
                Iterator<ProductCatalog> itr = list.iterator();
                while (itr.hasNext()) {
                    ProductCatalog p = itr.next();
                    if (p.getProduct().equals(prod)) {
                        list.remove(p);
                    }
                }
                if(prod!=null){
                    cart.setProductCatalog(list);
                }
                else{
                    cart.setProductCatalog(null);
                }
                cart = cartRepo.save(cart);
            }
            return DtoConverter.convert(cart);
        }
        catch(IndexOutOfBoundsException | NullPointerException | DataAccessException exp){
            logger.error("removeFromCart()");
            throw new CartServiceException(exp.getMessage());
        }
    }

    @Override
    public CartDto updateCart(Integer integer, ProductDto productDto) {
        logger.debug("updateCart("+integer.intValue()+":"+productDto.toString()+")");
        try{
            boolean newFlag = true;
            Product prod = prodRepo.findById(productDto.getId()).orElse(null);
            User user = userRepo.findById(integer).orElse(null);
            Cart cart = user != null && user.getCart() != null ? user.getCart() : null;
            if (cart.getProductCatalog() != null && !cart.getProductCatalog().isEmpty()) {
                CopyOnWriteArrayList<ProductCatalog> list = new CopyOnWriteArrayList<>(cart.getProductCatalog());
                Iterator<ProductCatalog> itr = list.iterator();
                while (itr.hasNext()) {
                    ProductCatalog p = itr.next();
                    if (p.getProduct().equals(prod)) {
                        if (productDto.getQuantity() != 0) {
                            p.setQuantity(productDto.getQuantity());
                        } else {
                            list.remove(p);
                        }
                        newFlag = false;
                    }
                }
                if (newFlag && productDto.getQuantity() > 0)
                    list.add(new ProductCatalog(cart, prod, productDto.getQuantity()));
                cart.setProductCatalog(list);
                cart = cartRepo.save(cart);
            }
            return DtoConverter.convert(cart);
        }
        catch(IndexOutOfBoundsException | NullPointerException | DataAccessException exp){
            logger.error("updateCart()");
            throw new CartServiceException(exp.getMessage());
        }
    }
}
