package com.dsi.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
//@SpringBootApplication
public class SpringBootMvc {

    public static void main(String[] args) {

       ApplicationContext context =  SpringApplication.run(SpringBootMvc.class, args);




//        SellerRepository userRepository = context.getBean(SellerRepository.class);
//        Seller user = new Seller();
//        user.setEmail("email@gmail.com");
//        user.setName("email");
//        Seller user1 = userRepository.save(user);
//
//        System.out.println(user1);

    }

}


