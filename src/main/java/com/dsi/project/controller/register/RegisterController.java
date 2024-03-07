package com.dsi.project.controller.register;

import com.dsi.project.model.Seller;
import com.dsi.project.model.User;
import com.dsi.project.service.ProductService;
import com.dsi.project.service.SellerService;
import com.dsi.project.service.UserService;
import jakarta.validation.Valid;
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
import java.sql.SQLException;

@Controller

public class RegisterController {
    public SellerService sellerService;

    public ProductService productService;

    public UserService userService;

    public RegisterController(SellerService sellerService, ProductService productService, UserService userService) {
        this.sellerService = sellerService;
        this.productService = productService;
        this.userService = userService;
    }

    @ModelAttribute
    public void getPrincipal(Principal principal, Model model){
        System.out.println("hi from seller");
        model.addAttribute("principal", principal);
    }
//    @PreAuthorize("hasAnyRole('SELLER','ADMIN')")
    @PostMapping(value = "/addSeller")
    public String addSeller(@Valid @ModelAttribute("seller") Seller seller, BindingResult result) {

        if(result.hasErrors()){ // this will not get  sql multiple key error
            return "sellerForm";
        }
        if(sellerService.isNewSellerService(seller.getEmail())){
            result.addError(new FieldError("seller", "email", "this email already has an account!"));
            return "sellerForm";
        }
        sellerService.saveSellerService(seller);

        return "sellerForm";

    }


//    @PreAuthorize("hasAnyRole('SELLER','ADMIN')")
    @GetMapping(value = "/serllerRegForm")
    public ModelAndView sellerForm(Model model){
        model.addAttribute("seller",new Seller());
        return new ModelAndView("sellerForm");
    }


//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/userRegForm")
    public ModelAndView addUser(Model model){
        Principal principal = (Principal) model.getAttribute("principal");

        model.addAttribute("user",new User());
        ModelAndView modelAndView = new ModelAndView("userRegForm");

        return modelAndView;
    }
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/addUser")
    public ModelAndView addUser(@Valid @ModelAttribute User user, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("home");

        String password = user.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        password = bCryptPasswordEncoder.encode(password);
        user.setPassword(password);
        System.out.println(user);

        try{
            userService.saveUserService(user);
        }catch (Exception sqlException){
            System.out.println(sqlException);
            result.addError(new FieldError("seller", "email", "this email already has an account!"));
            modelAndView.setViewName("userRegForm");

        }

        return modelAndView;


    }
}
