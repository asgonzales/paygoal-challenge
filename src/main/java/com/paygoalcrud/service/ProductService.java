
package com.paygoalcrud.service;

import com.paygoalcrud.entity.Product;
import com.paygoalcrud.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository repo;
    
    public List<Product> findAll() {
        List<Product> products = repo.findAll();
        return products;
    }
    
    public Product findById(String id) throws Exception{
        if(id == null || id.isEmpty()) throw new Exception("el id no puede estar vacío");
        Product product = repo.getById(Integer.valueOf(id));
        if(product == null) throw new Exception("No se encontró un producto con el id " + id);
        return product;
    }
}
