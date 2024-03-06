package com.dsi.project.config;

import com.dsi.project.model.AllUser;
import com.dsi.project.model.User;
import com.dsi.project.repository.AllUserRepository;
import com.dsi.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AllUserRepository allUserRepository;

    public UserDetailServiceImpl() {
    }

//    public UserDetailServiceImpl(AllUserRepository allUserRepository) {
//        this.allUserRepository = allUserRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AllUser allUser = allUserRepository.findByEmail(email);

        if(allUser == null) {
            System.out.println("no user found?");
        throw new UsernameNotFoundException("could not find user");
        }
        System.out.println("user found?");
        CustomUserDetails customUserDetails = new CustomUserDetails(allUser);
        System.out.println(allUser.toString());

        return customUserDetails;
    }


}
