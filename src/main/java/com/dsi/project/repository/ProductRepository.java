package com.dsi.project.repository;

import com.dsi.project.model.Product;
import com.dsi.project.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    public List<Product> findByUser(User user);
}
