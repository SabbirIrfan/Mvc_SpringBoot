package com.dsi.project.repository;

import com.dsi.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
     User findByEmail(String email);
     List<User> findByRoles_Name(String roleName);

}
