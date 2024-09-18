package com.dsi.project.service;

import java.util.List;
import java.util.Optional;

import com.dsi.project.model.User;
import com.dsi.project.repository.ProductRepository;
import com.dsi.project.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Override
    public Product getProductById(int id) {
        Product product = null;
        Optional<Product> optionalProduct =  productRepository.findById(id);

        if(optionalProduct.isPresent()){
            product = optionalProduct.get();
            return product;
        }
        return  product;
    }

    @Override
    public List<Product> getProductByUser(User user) {
        return  productRepository.findByUser(user );
    }



    @Override
    @Autowired
    public void setProductDao(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void saveProduct(Product product){
        this.productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Page<Product> getAllAvailableProduct(Pageable pageable) {

        Page<Product> availableProducts = productRepository.findAllAvailableProducts(pageable);
        return availableProducts;
    }
    @Override
    public Page<Product> getSearchedProduct(Pageable pageable, String query) {
        Page<Product> searchedProducts = productRepository.findSearchedProducts(pageable,query);
        return searchedProducts;
    }


}
