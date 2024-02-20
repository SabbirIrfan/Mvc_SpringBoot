package com.dsi.project.repository;

import com.dsi.project.model.Product;
import com.dsi.project.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    public List<Product> findByUser(User user);
}
