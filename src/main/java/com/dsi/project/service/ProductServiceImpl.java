package com.dsi.project.service;

import com.dsi.project.model.Product;
import com.dsi.project.model.User;
import com.dsi.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the ProductService interface providing CRUD operations
 * and custom queries for Product entities.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    /**
     * Constructor injection for ProductRepository.
     *
     * @param productRepository the product repository
     */
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Fetch a product by its ID.
     *
     * @param id the ID of the product
     * @return the product if found, otherwise null
     */
    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }



    /**
     * Save a product to the database.
     *
     * @param product the product to be saved
     */
    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    /**
     * Fetch all products from the database.
     *
     * @return a list of all products
     */
    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    /**
     * Fetch available products with pagination.
     *
     * @param pageable the pagination information
     * @return a paginated list of available products
     */
    @Override
    public Page<Product> getAvailableProduct(Pageable pageable) {
        return productRepository.findAvailableProducts(pageable);
    }

    /**
     * Fetch available products with pagination-
     *
     * @return a list of available products
     */
    @Override
    public List<Product> getAvailableProduct() {
        return productRepository.findAvailableProductsList();
    }


    /**
     * Search products by query with pagination.
     *
     * @param pageable the pagination information
     * @param query the search keyword
     * @return a paginated list of products matching the search query
     */
    @Override
    public Page<Product> getSearchedProduct(Pageable pageable, String query) {
        return productRepository.findSearchedProducts(pageable, query);
    }

    /**
     * Get all products associated with a specific user.
     *
     * @param user the user owning the products
     * @return a list of products
     */
    @Override
    public Page<Product> getProductsByUser(Pageable pageable, Integer userId) {
        return productRepository.getProductsByUserId(pageable, userId);
    }
}
