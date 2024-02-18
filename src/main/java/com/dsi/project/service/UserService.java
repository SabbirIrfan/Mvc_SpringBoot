package com.dsi.project.service;

import com.dsi.project.model.Seller;
import com.dsi.project.model.User;
import com.dsi.project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
@Service
public class UserService {

    UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getAllUserService(){
        return  userRepository.findAll();
    }
    public boolean isNewUserService(String email){
        return userRepository.findByEmail(email) == null;
    }
    public void saveUserService(User user){
        userRepository.save(user);
    }
    public void updateUserService(User user){
        userRepository.save(user);

    }
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
