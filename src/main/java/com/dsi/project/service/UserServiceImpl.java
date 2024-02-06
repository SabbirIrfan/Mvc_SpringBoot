package com.dsi.project.service;

import com.dsi.project.dao.UserRepository;
import com.dsi.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserService(int id) {
        return null;
    }

    @Override
    public boolean isNewUserService(int id) {
        return false;
    }

    @Override
    public void saveUserService(User user) {

    }

    @Override
    public void updateUserService(User user) {

    }

    @Override
    public List<User> getUserByEmail(String email) {
        return null;
    }
}
