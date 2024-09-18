package com.dsi.project.service;


import com.dsi.project.model.User;
import com.dsi.project.repository.ProductRepository;
import com.dsi.project.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
//    public ProductDao getProductDao();
    public Product getProductById(int id);
    public List<Product> getProductByUser(User user);
    public void setProductDao(ProductRepository productRepository);

    public void saveProduct(Product product);
    public List<Product> getAllProduct();
    public Page<Product> getAllAvailableProduct(Pageable pageable);

    public Page<Product> getSearchedProduct(Pageable pageable, String query);
    public Page<Product> getProductsBySeller(Pageable pageable, Integer sellerId);

}
