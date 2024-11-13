package com.dsi.project.service;

import com.dsi.project.model.Product;
import com.dsi.project.model.Role;
import com.dsi.project.model.User;
import com.dsi.project.repository.ProductRepository;
import com.dsi.project.repository.RoleRepository;
import com.dsi.project.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;

    private final ProductService productService;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, ProductRepository productRepository, ProductService productService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
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
        Optional<User> existingUserOpt = userRepository.findById(user.getId());
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setEmail(user.getEmail());
            existingUser.setName(user.getName());
            existingUser.setRoles(user.getRoles());
            userRepository.save(existingUser);
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

    /**
     * Get products sold by a specific user.
     */
    public Page<Product> getProductsSoldByUser(Pageable pageable, Integer userId) {
        return productRepository.findBySellerId(pageable, userId);
    }

    /**
     * Get products bought by a specific user.
     */
    public Page<Product> getProductsBoughtByUser(Pageable pageable, Integer userId) {
        return productRepository.findByBuyerId(pageable, userId);
    }


    public boolean hasRole(User user, String roleName) {
        return user.getRoles().stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(roleName));
    }
    public Page<Product> getProductsByUserRole(Pageable pageable, Integer userId) {
        // Fetch the user by ID
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return Page.empty(pageable); // Return empty page if user is not found
        }

        // Check if the user has the 'SELLER' role or 'BUYER' role
        if (hasRole(user, "SELLER")) {
            // Fetch products sold by the seller
            return productService.getProductsBySeller(pageable, userId);
        } else if (hasRole(user, "BUYER")) {
            // Fetch products bought by the buyer
            return productService.getProductsByBuyer(pageable, userId);
        }

        // If the user does not have a valid role, return an empty page
        return Page.empty(pageable);
    }
}
