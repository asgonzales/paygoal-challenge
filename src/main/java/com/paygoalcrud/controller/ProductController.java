
package com.paygoalcrud.controller;

import com.paygoalcrud.ErrorHandler.APIException;
import com.paygoalcrud.dto.PersistProductDTO;
import com.paygoalcrud.dto.ProductDTO;
import com.paygoalcrud.entity.Product;
import com.paygoalcrud.response.ResponseHandler;
import com.paygoalcrud.service.ProductService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    ProductService service;
    
    @GetMapping("")
    public ResponseEntity getByName(@RequestParam Optional<String> name) {
        String nameValue = name.orElse("");
        try {
            if(name == null || nameValue.isEmpty()) {
                List<ProductDTO> allProducts = service.findAll();
                return ResponseHandler.success(HttpStatus.OK, allProducts);
            } else {
                List<ProductDTO> products = service.findByName(nameValue);
                return ResponseHandler.success(HttpStatus.OK, products);
            }
        } catch (APIException ex) {
            return ResponseHandler.failed(ex.getHttpStatus(), ex.getMessage());
        } catch(Exception ex) {
            return ResponseHandler.failed(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id) {
        try {
            ProductDTO product = service.findById(id);
            return ResponseHandler.success(HttpStatus.OK, product);
        } catch (APIException ex) {
            return ResponseHandler.failed(ex.getHttpStatus(), ex.getMessage());
        } catch(Exception ex) {
            return ResponseHandler.failed(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
    @PostMapping("")
    public ResponseEntity createProduct(@RequestBody PersistProductDTO product) {
        try {
            ProductDTO createdProduct = service.createProduct(product);
            return ResponseHandler.success(HttpStatus.CREATED, createdProduct);
        } catch (APIException ex) {
            return ResponseHandler.failed(ex.getHttpStatus(), ex.getMessage());
        } catch(Exception ex) {
            return ResponseHandler.failed(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable String id, @RequestBody PersistProductDTO product) {
        try {
            ProductDTO updatedProduct = service.updateProduct(id, product);
            return ResponseHandler.success(HttpStatus.OK, updatedProduct);
        } catch (APIException ex) {
            return ResponseHandler.failed(ex.getHttpStatus(), ex.getMessage());
        } catch(Exception ex) {
            return ResponseHandler.failed(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id) {
        try {
            ProductDTO deletedProduct = service.deleteProduct(id);
            return ResponseHandler.success(HttpStatus.OK, deletedProduct);
        } catch (APIException ex) {
            return ResponseHandler.failed(ex.getHttpStatus(), ex.getMessage());
        } catch(Exception ex) {
            return ResponseHandler.failed(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
