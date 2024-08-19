package com.dsi.project.controller.register;

import com.dsi.project.model.AllUser;
import com.dsi.project.model.Seller;
import com.dsi.project.model.User;
import com.dsi.project.service.AllUserService;
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

@Controller

public class RegisterController {
    public SellerService sellerService;

    public ProductService productService;

    public UserService userService;

    public AllUserService allUserService;


    private String setPassword(AllUser allUser){
        String password = allUser.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        password = bCryptPasswordEncoder.encode(password);

        return password;

    }
    public RegisterController(SellerService sellerService, ProductService productService, UserService userService, AllUserService allUserService) {
        this.sellerService = sellerService;
        this.productService = productService;
        this.userService = userService;
        this.allUserService = allUserService;
    }

    @ModelAttribute
    public void getPrincipal(Principal principal, Model model){
        System.out.println("REGISTER CONTROLLER");
        model.addAttribute("principal", principal);
    }
//    @PreAuthorize("hasAnyRole('SELLER','ADMIN')")
    @PostMapping(value = "/addSeller")
    public String addSeller(@Valid @ModelAttribute  AllUser allUser, BindingResult result ,Model model) {

        if(result.hasErrors()){ // this will not get  sql multiple key error
            return "sellerForm";
        }
        if(!allUserService.isNewUserService(allUser.getEmail())){
            result.addError(new FieldError("seller", "email", "this email already has an account!"));
            model.addAttribute("error", "this email already has an account!");

            return "sellerForm";

        }
        Seller seller = new Seller();

        String password  = setPassword(allUser);
        allUser.setPassword(password);

        seller.setEmail(allUser.getEmail());
        allUserService.saveUserService(allUser, true);
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
    @GetMapping("/registerUser")
    public ModelAndView addUser(Model model){
        Principal principal = (Principal) model.getAttribute("principal");

        model.addAttribute("user",new User());
        ModelAndView modelAndView = new ModelAndView("registerUser");

        return modelAndView;
    }
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/registerUser")
    public ModelAndView addUser(@Valid @ModelAttribute AllUser allUser, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("home");

        String password  = setPassword(allUser);
        allUser.setPassword(password);

        System.out.println(allUser);
        User user = new User();
        user.setEmail(allUser.getEmail());
        allUser.setRole("ROLE_USER");

        System.out.println("i am here at regi cont line 98");
        try{
            userService.saveUserService(user);
            allUserService.saveUserService(allUser, false);
        }catch (Exception sqlException){
            System.out.println("i am here at regi cont line 103");
            System.out.println(sqlException);
            result.addError(new FieldError("user", "email", "this email already has an account!"));
            modelAndView.setViewName("registerUser");
            modelAndView.addObject("error", "this email already has an account!");

            return modelAndView;

        }

        return modelAndView;


    }
}
