package com.dsi.project.service;

import com.dsi.project.model.User;
import com.dsi.project.repository.ProductRepository;
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
    public List<Product> getAllAvailableProduct() {
        List<Product> all = (List<Product>) productRepository.findAll();
        all.removeIf(product -> product.getStatus() == 0 || product.getStatus()==2);
        return all;
    }


}
