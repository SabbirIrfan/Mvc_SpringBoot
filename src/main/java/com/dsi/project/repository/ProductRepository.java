package com.dsi.project.repository;

import com.dsi.project.model.Product;
import com.dsi.project.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    public List<Product> findByUser(User user);
    @Query("SELECT p FROM Product p WHERE p.status <> 0 AND p.status <> 2")
    Page<Product> findAllAvailableProducts(Pageable pageable);
}
