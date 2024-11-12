package com.dsi.project.service;

import com.dsi.project.model.Product;
import com.dsi.project.model.Role;
import com.dsi.project.model.User;
import com.dsi.project.repository.ProductRepository;
import com.dsi.project.repository.RoleRepository;
import com.dsi.project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final ProductRepository productRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserById(int userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Iterable<User> getUsers(String role) {
        return userRepository.findByRoles_Name(role);
    }

    public boolean isNewUser(String email) {
        return userRepository.findByEmail(email) == null;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        Optional<User> updatingUserOptional = userRepository.findById(user.getId());
        if (updatingUserOptional.isPresent()) {
            User updatedUser = updatingUserOptional.get();
            updatedUser.setEmail(user.getEmail());
            updatedUser.setName(user.getName());
            updatedUser.setRoles(user.getRoles());
            userRepository.save(updatedUser);
        }
    }

    public void assignRoleToUser(Integer userId, String roleName) {
        User user = getUserById(userId);
        if (user != null) {
            Role role = roleRepository.findByName(roleName);
            if (role != null) {
                user.getRoles().add(role);
                userRepository.save(user);
            }
        }
    }

    public Role getRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

}
