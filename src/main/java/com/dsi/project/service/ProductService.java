package com.dsi.project.service;

import com.dsi.project.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

      Product getProductById(int id);

      void saveProduct(Product product);

      List<Product> getAllProduct();

      Page<Product> getAvailableProduct(Pageable pageable);

      List<Product> getAvailableProduct();

      Page<Product> getSearchedProduct(Pageable pageable, String query);

      Page<Product> getProductsBySeller(Pageable pageable, Integer sellerId);

      Page<Product> getProductsByBuyer(Pageable pageable, Integer buyerId);

      Product assignBuyerToProduct(Integer productId, Integer buyerId);

      Product assignSellerToProduct(Integer productId, Integer sellerId);
}
