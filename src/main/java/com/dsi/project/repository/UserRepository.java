package com.dsi.project.repository;

import com.dsi.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

     // Find a user by email
     User findByEmail(String email);

     // Find users by role name
     List<User> findByRoles_Name(String roleName);

     // Find a user by email



}
