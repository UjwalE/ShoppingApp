package com.springboot.shoppingapp.dto;

import com.springboot.shoppingapp.entity.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DtoConverter {

    public static UserDto convert(User user) {
        UserDto userDto = null;
        if (user != null) {
            userDto = new UserDto();
            userDto.setId(user.getUserId());
            userDto.setName(user.getUserName());
            userDto.setCart_id(user.getCart().getCartId());
        }
        return userDto;
    }

    public static CartDto convert(Cart cart) {
        CartDto cartDto = null;
        if (cart != null) {
            cartDto = new CartDto();
            cartDto.setProducts(convert(cart.getProductCatalog()));
            cartDto.setTotal(getTotal(cartDto));
            cartDto.setUser(cart.getUser().getUserName());
        }
        return cartDto;
    }

    public static List<ProductDto> convert(List<ProductCatalog> products) {
        List<ProductDto> list = null;
        if (products != null && !products.isEmpty()) {
            list = new ArrayList<>();
            Iterator<ProductCatalog> itr = products.iterator();
            while (itr.hasNext()) {
                list.add(convert(itr.next()));
            }
        }
        return list;
    }

    private static ProductDto convert(ProductCatalog next) {
        ProductDto proDto = new ProductDto();
        proDto.setId(next.getProduct().getProdId());
        proDto.setName(next.getProduct().getProductName());
        proDto.setPrice(next.getProduct().getPrice());
        proDto.setQuantity(next.getQuantity());
        proDto.setSum(next.getProduct().getPrice() * next.getQuantity());
        return proDto;
    }

    private static float getTotal(CartDto cartDto) {

        List<ProductDto> products = cartDto.getProducts();
        float total = 0;
        if (products != null && !products.isEmpty()) {
            Iterator<ProductDto> itr = products.iterator();
            while (itr.hasNext()) {
                total += itr.next().getSum();
            }
        }
        return total;
    }

    public static ProductSearchDto convert(Product product){
        ProductSearchDto dto = new ProductSearchDto();
        dto.setId(product.getProdId());
        dto.setName(product.getProductName());
        dto.setPrice(product.getPrice());

        if(product instanceof Apparal){
            Apparal apparal = (Apparal)product;
            dto.setType(apparal.getType());
            dto.setDesign(apparal.getDesign());
            dto.setBrand(apparal.getBrand());
        }
        else if(product instanceof Book){
            Book book = (Book)product;
            dto.setGenre(book.getGenre());
            dto.setAuthor(book.getAuthor());
            dto.setPublication(book.getPublication());
        }
        return dto;
    }
}
