package com.dsi.project.controller.user;

import com.dsi.project.model.Product;
import com.dsi.project.model.User;
import com.dsi.project.service.ProductService;
import com.dsi.project.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProfileController {
    UserService userService;
    ProductService productService;

    public ProfileController(UserService userService, ProductService productService) {

        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/userRegForm")
    public ModelAndView addUser(Model model){
        model.addAttribute("user",new User());
        ModelAndView modelAndView = new ModelAndView("userRegForm");

        return modelAndView;
    }


    @GetMapping("/showUsers")
    public ModelAndView showUsers(){
        ModelAndView modelAndView = new ModelAndView("showUsers");

        Iterable<User> users = userService.getAllUserService();

        modelAndView.addObject("users", users);
        return modelAndView;
    }


    @PostMapping("/addUser")
    public ModelAndView addUser(@ModelAttribute User user){
        ModelAndView modelAndView = new ModelAndView("home");

        System.out.println(user);

        userService.saveUserService(user);
        return modelAndView;


    }
    @PostMapping("/editUserForm") // need to fix this ambiguity :: maybe with a editing view
    public ModelAndView showEditUser(@Param("userid") Integer userId){
        ModelAndView modelAndView = new ModelAndView("editUser");
        User user = userService.getUserById(userId);
        modelAndView.addObject("user",user);
        return modelAndView;
    }
    @PostMapping("/editUser")
    public ModelAndView editUser(@ModelAttribute User user, @Param("userId") int userId){
        ModelAndView modelAndView = new ModelAndView("home");

        System.out.println(user);

        userService.updateUserService(user,userId);
        List<Product> productList = productService.getAllAvailableProduct();
        modelAndView.addObject("productList", productList);
        return modelAndView;


    }

}
