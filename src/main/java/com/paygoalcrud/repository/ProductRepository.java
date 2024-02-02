

package com.paygoalcrud.repository;

import com.paygoalcrud.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    @Query("SELECT p FROM Product p WHERE p.id = :id")
    public Product getProductById(@Param("id") int id);
    
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    public List<Product> getByName(@Param("name") String name);
    
}
