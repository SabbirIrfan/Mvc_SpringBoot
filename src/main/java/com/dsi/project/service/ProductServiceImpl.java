package com.dsi.project.service;

import com.dsi.project.dao.ProductRepository;
import com.dsi.project.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
