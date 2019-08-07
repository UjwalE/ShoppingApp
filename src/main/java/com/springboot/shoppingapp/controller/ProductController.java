package com.springboot.shoppingapp.controller;

import com.springboot.shoppingapp.dto.ProductSearchDto;
import com.springboot.shoppingapp.dto.ProductSearchRequestDto;
import com.springboot.shoppingapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    public List findAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductSearchDto find(@PathVariable(name = "id")int id) {
        return productService.getById(id);
    }

    @PostMapping()
    public List find(@RequestBody ProductSearchRequestDto dto) {
        return productService.getByName(dto.getSearch());
    }
}
