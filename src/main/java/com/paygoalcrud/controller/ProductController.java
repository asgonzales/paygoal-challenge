
package com.paygoalcrud.controller;

import com.paygoalcrud.entity.Product;
import com.paygoalcrud.response.ResponseHandler;
import com.paygoalcrud.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    ProductService service;
    
    @GetMapping("/test")
    public String test() {
        return "hola";
    }
    @GetMapping("")
    public ResponseEntity getAllProducts() {
        try {
            List<Product> allProducts = service.findAll();
            return ResponseHandler.success(HttpStatus.OK, allProducts);
        } catch(Exception ex) {
            return ResponseHandler.failed(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id) {
        try {
            Product product = service.findById(id);
            return ResponseHandler.success(HttpStatus.OK, product);
        } catch(Exception ex) {
        return ResponseHandler.failed(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
    
}
