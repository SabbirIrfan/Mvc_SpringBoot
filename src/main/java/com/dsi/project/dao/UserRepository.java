package com.dsi.project.dao;

import com.dsi.project.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    public List<User> findUserByEmail(String email);

}
