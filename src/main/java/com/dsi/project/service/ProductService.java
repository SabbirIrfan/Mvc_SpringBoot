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
      Product getProductById(int id);
      List<Product> getProductByUser(User user);
      void setProductDao(ProductRepository productRepository);

      void saveProduct(Product product);
      List<Product> getAllProduct();
      Page<Product> getAllAvailableProduct(Pageable pageable);

      Page<Product> getSearchedProduct(Pageable pageable, String query);
      Page<Product> getProductsBySeller(Pageable pageable, Integer sellerId);

}
