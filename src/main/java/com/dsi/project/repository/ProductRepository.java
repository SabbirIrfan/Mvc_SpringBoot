package com.dsi.project.repository;

import com.dsi.project.model.Product;
import com.dsi.project.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    public List<Product> findByUser(User user);
    @Query("SELECT p FROM Product p WHERE p.status <> 0 AND p.status <> 2")
    Page<Product> findAllAvailableProducts(Pageable pageable);
<<<<<<< HEAD
    @Query("SELECT p FROM Product p WHERE (p.brandName LIKE %:query% OR p.productModel LIKE %:query%) AND p.status <> 0 AND p.status <> 2")
    Page<Product> findSearchedProducts(Pageable pageable, @Param("query") String query);
    

=======
>>>>>>> fa9b18a... feat pagination added
}
