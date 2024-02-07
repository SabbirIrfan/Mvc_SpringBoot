package com.dsi.project.controller;

import com.dsi.project.model.User;
import com.dsi.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user ){
        ModelAndView modelAndView = new ModelAndView();

        userService.saveUserService(user);
        System.out.println(user);
        modelAndView.setViewName("userForm");
//        System.out.println(userService.getAllUserService());
        return modelAndView;


    }
}
