package com.dsi.project;

import com.dsi.project.dao.UserRepository;
import com.dsi.project.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

       ApplicationContext context =  SpringApplication.run(DemoApplication.class, args);

        UserRepository userRepository = context.getBean(UserRepository.class);

        User user = new User();
        user.setEmail("email@gmail.com");
        user.setName("email");
        User user1 = userRepository.save(user);

        System.out.println(user1);

    }

}


