package com.dsi.project.service;


import com.dsi.project.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    public List<User> getAllUserService();
    public boolean isNewUserService(String email);
    public void saveUserService(User user);
    public void updateUserService(User user);
    public List<User> getUserByEmail(String email);
}
