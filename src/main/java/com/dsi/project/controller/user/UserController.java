package com.dsi.project.controller.user;

import com.dsi.project.model.User;
import com.dsi.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user ) {
        ModelAndView modelAndView = new ModelAndView();
        userService.saveUserService(user);
        modelAndView.setViewName("userForm");
        return modelAndView;

    }
    @GetMapping(value = "/userForm")
    public ModelAndView userForm(Model model){
        return new ModelAndView("userForm");

    }
}
