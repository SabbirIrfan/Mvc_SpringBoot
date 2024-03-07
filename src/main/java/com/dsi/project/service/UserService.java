package com.dsi.project.service;

import com.dsi.project.model.User;
import com.dsi.project.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public User getUserById(int userId){
        Optional<User> user =  userRepository.findById(userId);

        return user.orElse(null);
    }

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
    public void updateUserService(User user, Integer userId ){

        Optional<User> updatingUserOptional = userRepository.findById(userId);
        if(updatingUserOptional.isPresent()){
            User updatedUser =  updatingUserOptional.get();
            updatedUser.setEmail(user.getEmail());
            userRepository.save(updatedUser);

            return;
        }

        System.out.println( "could not update");
    }

}
