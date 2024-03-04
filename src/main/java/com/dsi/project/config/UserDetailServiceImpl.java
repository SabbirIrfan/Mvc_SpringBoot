package com.dsi.project.config;

import com.dsi.project.model.User;
import com.dsi.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetailServiceImpl() {
    }

//    public UserDetailServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if(user == null) {
            System.out.println("no such user?");
        throw new UsernameNotFoundException("could not find user");
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        System.out.println(user.getPassword());

        return customUserDetails;
    }


}
