package com.dsi.project.controller.register;

import java.util.Set;
import java.util.HashSet;

import com.dsi.project.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;
import com.dsi.project.model.Role;
import com.dsi.project.model.User;
import com.dsi.project.service.UserService;

@Controller
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    private String setPassword(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(user.getPassword());
    }

    @ModelAttribute
    public void getPrincipal(Principal principal, Model model) {
        model.addAttribute("principal", principal);
    }

    @GetMapping("/registerUser")
    public ModelAndView registerUser(Model model) {
        model.addAttribute("user", new User());
        return new ModelAndView("registerUser");
    }

    @PostMapping("/registerUser")
    public ModelAndView addUser(@Valid @ModelAttribute User user,
                                @ModelAttribute("roleType") String roleType,
                                BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("registerUser");


        logger.info("Registering user as " + roleType);

        if (result.hasErrors()) {
            modelAndView.setViewName("registerUser");
            logger.warn("Found error while registering user");
            return modelAndView;
        }

        user.setPassword(setPassword(user));

        // Fetch the role from the database instead of creating a new one
        Set<Role> roles = new HashSet<>();
        try {
            Role role;
            if ("SELLER".equalsIgnoreCase(roleType)) {
                role = userService.getRoleByName("ROLE_SELLER");
            } else {
                role = userService.getRoleByName("ROLE_USER");
            }

            if (role == null) {
                throw new Exception("Role not found");
            }

            roles.add(role);
            user.setRoles(roles);

            if (userService.isNewUser(user.getEmail())) {
                userService.saveUser(user);
                modelAndView.setViewName("registerUser");
                modelAndView.addObject("isSuccess",true);
            } else {
                throw new Exception("User already exists");
            }
        } catch (Exception error) {
            result.addError(new FieldError("user", "email", "This email already has an account!"));
            modelAndView.setViewName("registerUser");
            modelAndView.addObject("error", "This email already has an account!");

            logger.warn("FOUND EXCEPTION WHILE SAVING USER WITH ERROR: " + error);
            return modelAndView;
        }

        return modelAndView;
    }


}
