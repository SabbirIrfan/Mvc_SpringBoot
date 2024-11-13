package com.dsi.project.controller.user;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(OtherController.class);
    UserService userService;
    ProductService productService;
    @ModelAttribute
    public void getPrincipal(HttpSession session,Principal principal, Model model){
        System.out.println("hi from other");
        model.addAttribute("principal", principal);

        if(principal != null){
            User user = userService.getUserByEmail(principal.getName());
            session.setAttribute("userId", user.getId());
        }
    }

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
        modelAndView.setViewName("buyingForm");
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping(path = "/orderProduct")
    public ModelAndView orderProduct(@RequestParam("email") String email,
                                     @RequestParam("id") Integer productId) {
        ModelAndView modelAndView = new ModelAndView();
        if (userService.isNewUser(email)) {
            modelAndView.addObject("emailError", "The email you entered is not registered. please register first!");
            modelAndView.setViewName("buyingForm");
            return modelAndView;
        }

        User user = userService.getUserByEmail(email);
        Product boughtProduct = productService.getProductById(productId);
        boughtProduct.setStatus((byte) 2);

        if (user == null) {
            System.out.println("Wrong Email");
            return modelAndView;
        } else {
            boughtProduct.setBuyer(user);
            List<Product> productsList = user.getProductsBought();
            productsList.add(boughtProduct);
            productService.saveProduct(boughtProduct);

        }
        modelAndView.setViewName("home");

        return modelAndView;
    }




    @PreAuthorize("hasAnyRole('ADMIN','USER','SELLER')")
    @GetMapping("/products")
    public ModelAndView Product(@RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "9") int size,
                                @RequestParam("userId") Integer userId,
                                Model model) {
        logger.info("Fetching products for user with id : "+ userId);
        ModelAndView modelAndView = new ModelAndView("products");
        Pageable pageable = PageRequest.of(page, size);

        User user = userService.getUserById(userId);
        modelAndView.addObject("user", user);

        Page<Product> productPage = userService.getProductsByUserRole(pageable,userId);


        modelAndView.addObject("products", productPage.getContent());
        modelAndView.addObject("totalPages", productPage.getTotalPages());
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("userId", userId);
        modelAndView.setViewName("products");

        logger.info("Fetched products for user with id : "+ userId);
        return modelAndView;

    }






}
