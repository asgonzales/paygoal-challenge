
package com.paygoalcrud.service;

import com.paygoalcrud.ErrorHandler.APIException;
import com.paygoalcrud.dto.ProductDTO;
import com.paygoalcrud.dto.PersistProductDTO;
import com.paygoalcrud.entity.Product;
import com.paygoalcrud.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository repo;
    @Autowired
    ModelMapper modelMapper;
    
    public List<ProductDTO> findAll() {
        List<Product> products = repo.findAll();
        List<ProductDTO> mappedProducts = new ArrayList<>();
        products.forEach(product -> {
            mappedProducts.add(modelMapper.map(product, ProductDTO.class));
        });
        return mappedProducts;
    }
    public List<ProductDTO> findAllOrderByPrice(String orderBy) {
        List<Product> products;
        if(orderBy.equals("asc"))products = repo.getAllOrderByPriceAsc();
        else products = repo.getAllOrderByPriceDesc();
        List<ProductDTO> mappedProducts = new ArrayList<>();
        products.forEach(product -> {
            mappedProducts.add(modelMapper.map(product, ProductDTO.class));
        });
        return mappedProducts;
    }
    
    public ProductDTO findById(String id) throws APIException, Exception{
        if(id == null || id.isEmpty()) throw new APIException(HttpStatus.UNPROCESSABLE_ENTITY, "el id no puede estar vacío");
        Product product = repo.getProductById(Integer.valueOf(id));
        if(product == null) throw new APIException(HttpStatus.NOT_FOUND, "No se encontró un producto con el id " + id);
        return modelMapper.map(product, ProductDTO.class);
    }
    
    public List<ProductDTO> findByName(String name) throws APIException, Exception {
        List<Product> products = repo.getByName(name);
        if(products.isEmpty()) throw new APIException(HttpStatus.NOT_FOUND, "No se encontraron productos con ese nombre");
        List<ProductDTO> mappedProducts = new ArrayList<>();
        products.forEach(product -> {
           mappedProducts.add(modelMapper.map(product, ProductDTO.class)); 
        });
        return mappedProducts;
    }
    public List<ProductDTO> findByNameOrderByPrice(String name, String orderBy) throws APIException, Exception {
        List<Product> products;
        if(orderBy.equals("asc")) products = repo.getByNameOrderByPriceAsc(name);
        else products = repo.getByNameOrderByPriceDesc(name);
        if(products.isEmpty()) throw new APIException(HttpStatus.NOT_FOUND, "No se encontraron productos con ese nombre");
        List<ProductDTO> mappedProducts = new ArrayList<>();
        products.forEach(product -> {
           mappedProducts.add(modelMapper.map(product, ProductDTO.class)); 
        });
        return mappedProducts;
    }
    
    public ProductDTO createProduct(PersistProductDTO product) throws Exception {
        if(product == null) throw new APIException(HttpStatus.UNPROCESSABLE_ENTITY, "El producto no puede estar vacío");
        if(product.getName() == null || product.getName().isEmpty()) throw new APIException(HttpStatus.UNPROCESSABLE_ENTITY, "El nombre del producto no puede estar vacío");
        if(product.getDescription() == null || product.getDescription().isEmpty()) throw new APIException(HttpStatus.UNPROCESSABLE_ENTITY, "La descripción del producto no puede estar vacía");
        if(product.getPrice() == 0) throw new APIException(HttpStatus.UNPROCESSABLE_ENTITY, "El precio del producto no puede ser 0");
        if(product.getStock() == 0) throw new APIException(HttpStatus.UNPROCESSABLE_ENTITY, "El stock del producto no puede ser 0");
        Product productMapped = modelMapper.map(product, Product.class);
        Product newProduct = repo.save(productMapped);
        return modelMapper.map(newProduct, ProductDTO.class);
    }
    
    public ProductDTO updateProduct(String id, PersistProductDTO product) throws Exception {
        if(product == null) throw new APIException(HttpStatus.UNPROCESSABLE_ENTITY, "El producto no puede estar vacío");
        if(Integer.valueOf(id) == 0) throw new APIException(HttpStatus.UNPROCESSABLE_ENTITY, "El id no puede ser 0");
        if(product.getName() == null || product.getName().isEmpty()) throw new APIException(HttpStatus.UNPROCESSABLE_ENTITY, "El nombre del producto no puede estar vacío");
        if(product.getDescription() == null || product.getDescription().isEmpty()) throw new APIException(HttpStatus.UNPROCESSABLE_ENTITY, "La descripción del producto no puede estar vacía");
        if(product.getPrice() == 0) throw new APIException(HttpStatus.UNPROCESSABLE_ENTITY, "El precio del producto no puede ser 0");
        if(product.getStock() == 0) throw new APIException(HttpStatus.UNPROCESSABLE_ENTITY, "El stock del producto no puede ser 0");
        Product productInDB = repo.getProductById(Integer.valueOf(id));
        if(productInDB == null) throw new APIException(HttpStatus.NOT_FOUND, "El producto no existe");
        Product productToSave = modelMapper.map(product, Product.class);
        productToSave.setId(Integer.valueOf(id));
        Product updatedProduct = repo.save(productToSave);
        return modelMapper.map(updatedProduct, ProductDTO.class);
    }
    
    public ProductDTO deleteProduct(String id) throws APIException, Exception {
        if(id.isEmpty()) throw new APIException(HttpStatus.UNPROCESSABLE_ENTITY, "El id no puede estar vacío");
        
        Product productInDB = repo.getProductById(Integer.valueOf(id));
        if(productInDB == null) throw new APIException(HttpStatus.NOT_FOUND, "No se encontró el producto");
        repo.deleteById(Integer.valueOf(id));
        return modelMapper.map(productInDB, ProductDTO.class);
    }
}
