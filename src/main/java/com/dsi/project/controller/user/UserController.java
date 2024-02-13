package com.dsi.project.controller.user;

import com.dsi.project.model.User;
import com.dsi.project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public ModelAndView addUser(@Valid  @ModelAttribute("user") User user, BindingResult result, RedirectAttributes redirectAttributes) {




        ModelAndView modelAndView = new ModelAndView();
        if(result.hasErrors()){ // this will not get  sql multiple key error
            modelAndView.setViewName("userForm");
            System.out.println(result.getAllErrors());
            return modelAndView;
        }
        userService.saveUserService(user);

        modelAndView.setViewName("userForm");
        return modelAndView;

    }
    @GetMapping(value = "/userForm")
    public ModelAndView userForm(Model model){
        return new ModelAndView("userForm");

    }
}
