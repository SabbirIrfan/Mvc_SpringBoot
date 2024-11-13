package com.dsi.project.service;

import com.dsi.project.model.Product;
import com.dsi.project.model.User;
import com.dsi.project.repository.ProductRepository;
import com.dsi.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getAvailableProduct(Pageable pageable) {
        return productRepository.findAvailableProducts(pageable);
    }

    @Override
    public List<Product> getAvailableProduct() {
        return productRepository.findAvailableProductsList();
    }

    @Override
    public Page<Product> getSearchedProduct(Pageable pageable, String query) {
        return productRepository.findSearchedProducts(pageable, query);
    }

    @Override
    public Page<Product> getProductsBySeller(Pageable pageable, Integer sellerId) {
        return productRepository.findBySellerId(pageable, sellerId);
    }

    @Override
    public Page<Product> getProductsByBuyer(Pageable pageable, Integer buyerId) {
        return productRepository.findByBuyerId(pageable, buyerId);
    }

    @Override
    public Product assignBuyerToProduct(Integer productId, Integer buyerId) {
        Optional<Product> productOpt = productRepository.findById(productId);
        Optional<User> buyerOpt = userRepository.findById(buyerId);

        if (productOpt.isPresent() && buyerOpt.isPresent()) {
            Product product = productOpt.get();
            product.setBuyer(buyerOpt.get());
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    public Product assignSellerToProduct(Integer productId, Integer sellerId) {
        Optional<Product> productOpt = productRepository.findById(productId);
        Optional<User> sellerOpt = userRepository.findById(sellerId);

        if (productOpt.isPresent() && sellerOpt.isPresent()) {
            Product product = productOpt.get();
            product.setSeller(sellerOpt.get());
            return productRepository.save(product);
        }
        return null;
    }
}
