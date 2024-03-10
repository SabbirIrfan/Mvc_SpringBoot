package com.dsi.project.controller.user;

import com.dsi.project.model.AllUser;
import com.dsi.project.model.Product;
import com.dsi.project.model.User;
import com.dsi.project.service.AllUserService;
import com.dsi.project.service.ProductService;
import com.dsi.project.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class ProfileController {

    @ModelAttribute
    public void getPrincipal(Principal principal, Model model){
        System.out.println("hi from ProfileController");
        model.addAttribute("principal", principal);
    }

    UserService userService;
    ProductService productService;
    AllUserService allUserService;

    public ProfileController(UserService userService, ProductService productService , AllUserService allUserService ) {

        this.userService = userService;
        this.productService = productService;
        this.allUserService = allUserService;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/userRegForm")
    public ModelAndView addUser(Model model){
        Principal principal = (Principal) model.getAttribute("principal");

        model.addAttribute("user",new User());
        ModelAndView modelAndView = new ModelAndView("userRegForm");

        return modelAndView;
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/addUser")
    public ModelAndView addUser(@ModelAttribute AllUser allUser){
        ModelAndView modelAndView = new ModelAndView("home");
        User user = new User();
        user.setEmail(allUser.getEmail());
        System.out.println(user);

        userService.saveUserService(user);
        allUserService.saveUserService(allUser);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/showUsers")
    public ModelAndView showUsers(){
        ModelAndView modelAndView = new ModelAndView("showUsers");

        Iterable<User> users = userService.getAllUserService();

        modelAndView.addObject("users", users);
        return modelAndView;
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/profile")
    public ModelAndView showUserProfile(Model model) {
        Principal principal = (Principal) model.getAttribute("principal");
        ModelAndView modelAndView = new ModelAndView("userProfile");
        System.out.println(principal.getName());
        User user = userService.getUserByEmail(principal.getName());
        System.out.println(user.toString());
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/editUserForm") // need to fix this ambiguity :: maybe with a editing view
    public ModelAndView showEditUser(@Param("userid") Integer userId){
        ModelAndView modelAndView = new ModelAndView("editUser");
        System.out.println("user id" + userId);
        User user = userService.getUserById(userId);
        modelAndView.addObject("user",user);
        return modelAndView;
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/editUser")
    public ModelAndView editUser(@ModelAttribute User user, @Param("userId") int userId, @Param("name") String name){
        ModelAndView modelAndView = new ModelAndView("home");

        System.out.println(userId+ " "+ name);
        user.setName(name);

        userService.updateUserService(user,userId);
        List<Product> productList = productService.getAllAvailableProduct();
        modelAndView.addObject("productList", productList);
        return modelAndView;


    }

}
