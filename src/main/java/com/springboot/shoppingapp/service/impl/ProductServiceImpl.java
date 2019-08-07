package com.springboot.shoppingapp.service.impl;

import com.springboot.shoppingapp.dto.DtoConverter;
import com.springboot.shoppingapp.dto.ProductSearchDto;
import com.springboot.shoppingapp.entity.Product;
import com.springboot.shoppingapp.exception.ProductServiceException;
import com.springboot.shoppingapp.repo.ApparalRepository;
import com.springboot.shoppingapp.repo.BookRepository;
import com.springboot.shoppingapp.repo.ProductRepository;
import com.springboot.shoppingapp.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductRepository pRepo;

    @Autowired
    BookRepository bookRepo;

    @Autowired
    ApparalRepository apparalRepo;

    @Override
    public List getAll() {
        try {
            logger.debug("getAll()");
            List<Product> list = pRepo.findAll();
            return getProductSearchListDto(list);
        } catch (DataAccessException ex) {
            logger.error("getAll()");
            throw new ProductServiceException(ex.getMessage());
        }

    }

    @Override
    public List getByName(String name) {
        try {
            logger.debug("getByName(" + name + ")");
            return getProductSearchListDto(pRepo.findByProductNameContains(name));
        } catch (DataAccessException|IndexOutOfBoundsException|NullPointerException ex) {
            logger.error("getByName()");
            throw new ProductServiceException(ex.getMessage());
        }


    }

    @Override
    public ProductSearchDto getById(int id) {
        try {
            logger.debug("getById(" + id + ")");
            Product product = pRepo.findById(id).orElse(null);
            return product != null ? DtoConverter.convert(product) : null;
        } catch (DataAccessException|IndexOutOfBoundsException ex) {
            logger.error("getById()");
            throw new ProductServiceException(ex.getMessage());
        }

    }

    private List getProductSearchListDto(List<Product> list) {
        logger.debug("getProductSearchListDto()");
        try {
            List<ProductSearchDto> dtoList = null;
            if (list != null && !list.isEmpty()) dtoList = new ArrayList<>();
            Iterator itr = list.iterator();
            while (itr.hasNext()) {
                Product product = (Product) itr.next();
                ProductSearchDto dto = DtoConverter.convert(product);
                dtoList.add(dto);
            }
            return dtoList;
        } catch (Exception ex) {
            logger.error("getProductSearchListDto()");
            throw new ProductServiceException(ex.getMessage());
        }
    }
}
