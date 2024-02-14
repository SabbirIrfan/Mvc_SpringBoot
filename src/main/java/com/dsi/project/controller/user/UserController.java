package com.dsi.project.controller.user;

import com.dsi.project.model.User;
import com.dsi.project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping(value = "/adduser")
    public String addUser(@Valid  @ModelAttribute("user") User user,BindingResult result) {

        if(result.hasErrors()){ // this will not get  sql multiple key error
            return "userForm";
        }
        if(!userService.isNewUserService(user.getEmail())){
            result.addError(new FieldError("user", "email", "this email already has an account!"));

            return  "userForm";
        }
        userService.saveUserService(user);

        return "userForm";

    }


    @GetMapping(value = "/userForm")
    public ModelAndView userForm(Model model){
        model.addAttribute("user",new User());

        return new ModelAndView("userForm");

    }
}
