package com.dsi.project;

import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// @EnableAutoConfiguration
// @ComponentScan
@SpringBootApplication
public class SpringBootMvc {

    public static void main(String[] args) {

       ApplicationContext context =  SpringApplication.run(SpringBootMvc.class, args);

    }

}


