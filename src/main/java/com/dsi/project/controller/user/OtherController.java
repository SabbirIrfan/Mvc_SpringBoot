package com.dsi.project.controller.user;

import com.dsi.project.model.Product;
import com.dsi.project.model.User;
import com.dsi.project.service.ProductService;
import com.dsi.project.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/user")
public class OtherController {

    @ModelAttribute
    public void getPrincipal(Principal principal, Model model){
        System.out.println("hi from other");
        model.addAttribute("principal", principal);
    }
    UserService userService;
    ProductService productService;

    public OtherController(UserService userService, ProductService productService) {

        this.userService = userService;
        this.productService = productService;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','SELLER')")
    @GetMapping("/buyProduct/{id}")
    public ModelAndView buyProduct(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Product product = productService.getProductById(id);
        modelAndView.addObject("product", product);
        modelAndView.setViewName("buyingForm.html");
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping(path = "/orderProduct")
    public ModelAndView orderProduct(@RequestParam("email") String email,
                                     @RequestParam("id") Integer productId) {
        ModelAndView modelAndView = new ModelAndView();
        if (userService.isNewUser(email)) {
            modelAndView.addObject("emailError", "The email you entered is not registered. please register first!");
            modelAndView.setViewName("buyingForm.html");
            return modelAndView;
        }

        User user = userService.getUserByEmail(email);
        Product boughtProduct = productService.getProductById(productId);
        boughtProduct.setStatus((byte) 2);

        if (user == null) {
            System.out.println("Wrong Email");
            return modelAndView;
        } else {
            boughtProduct.setUser(user);
            List<Product> productsList = user.getProducts();
            productsList.add(boughtProduct);
            productService.saveProduct(boughtProduct);

        }
        modelAndView.setViewName("home.html");

        return modelAndView;
    }



    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/products")
    public ModelAndView Product(@RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "9") int size,
                                @RequestParam("userId") Integer userId) {
        ModelAndView modelAndView = new ModelAndView("products");
        User user = userService.getUserById(userId);
        Pageable pageable = PageRequest.of(page, size);
        modelAndView.addObject("user", user);

        Page<Product> productPage = productService.getProductsByUser(pageable, userId);

        modelAndView.addObject("products", productPage.getContent());
        modelAndView.addObject("totalPages", productPage.getTotalPages());
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("userId", userId);
        modelAndView.setViewName("products");
        return modelAndView;

    }


}
