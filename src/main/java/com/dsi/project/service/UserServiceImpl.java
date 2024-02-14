package com.dsi.project.service;

import com.dsi.project.repository.UserRepository;
import com.dsi.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUserService() {
        return userRepository.getAllUser();
    }

    @Override
    public boolean isNewUserService(String email) {
        return userRepository.findUserByEmail(email).isEmpty();
    }

    @Override
    public void saveUserService(User user) {
        userRepository.save(user);
    }


    @Override
    public void updateUserService(User user) {

    }

    @Override
    public List<User> getUserByEmail(String email) {

        return userRepository.findUserByEmail(email);
    }
}
