package com.dsi.project.service;


import com.dsi.project.model.AllUser;
import org.springframework.stereotype.Service;

@Service
public interface AllUserService {

    public void saveUserService(AllUser allUser , boolean isSeller);
    public boolean isNewUserService(String email);

}
