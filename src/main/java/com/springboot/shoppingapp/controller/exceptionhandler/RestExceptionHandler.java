package com.springboot.shoppingapp.controller.exceptionhandler;

import com.springboot.shoppingapp.controller.ShoppingController;
import com.springboot.shoppingapp.exception.CartServiceException;
import com.springboot.shoppingapp.exception.ProductServiceException;
import com.springboot.shoppingapp.exception.UserServiceException;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler({CartServiceException.class, UserServiceException.class, ProductServiceException.class})
    protected ResponseEntity<ApiError> handleServiceException(RuntimeException ex, WebRequest request){
        ApiError error = new ApiError(HttpStatus.OK);
        logger.error(ex.getMessage());
        return new ResponseEntity<ApiError>(error, error.getStatus());
    }
}
