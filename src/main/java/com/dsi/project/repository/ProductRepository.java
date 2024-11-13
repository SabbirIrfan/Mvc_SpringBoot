package com.dsi.project.repository;

import com.dsi.project.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Fetch available products with pagination
    @Query("SELECT p FROM Product p WHERE p.status <> 0 AND p.status <> 2 ORDER BY p.id DESC")
    Page<Product> findAvailableProducts(Pageable pageable);

    // Fetch available products as a list
    @Query("SELECT p FROM Product p WHERE p.status <> 0 AND p.status <> 2 ORDER BY p.id DESC")
    List<Product> findAvailableProductsList();

    // Search products by query with pagination
    @Query("SELECT p FROM Product p WHERE (p.brandName LIKE %:query% OR p.productModel LIKE %:query%) AND p.status <> 0 AND p.status <> 2 ORDER BY p.id DESC")
    Page<Product> findSearchedProducts(Pageable pageable, @Param("query") String query);

    Page<Product> findBySellerId(Pageable pageable, Integer sellerId);

    // Fetch products by buyer ID with pagination
    Page<Product> findByBuyerId(Pageable pageable, Integer buyerId);


}
