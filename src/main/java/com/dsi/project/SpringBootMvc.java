package com.dsi.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootMvc {

    public static void main(String[] args) {

       ApplicationContext context =  SpringApplication.run(SpringBootMvc.class, args);

//        UserRepository userRepository = context.getBean(UserRepository.class);
//
//        User user = new User();
//        user.setEmail("email@gmail.com");
//        user.setName("email");
//        User user1 = userRepository.save(user);
//
//        System.out.println(user1);

    }

}


