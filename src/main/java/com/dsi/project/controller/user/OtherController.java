package com.dsi.project.controller.user;

import com.dsi.project.model.Product;
import com.dsi.project.model.User;
import com.dsi.project.service.ProductService;
import com.dsi.project.service.UserService;
import org.springframework.data.repository.query.Param;
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

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/buyProduct")
    public ModelAndView buyProduct() {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("hheello");
        List<Product> productList = productService.getAllAvailableProduct();
        modelAndView.addObject("availableProductList", productList);
        modelAndView.setViewName("buyingForm.html");
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/boughtProduct")
    public ModelAndView boughtProduct(@Param("id") Integer userId) {
        ModelAndView modelAndView = new ModelAndView("boughtProduct");
        User user = userService.getUserById(userId);
        List<Product> productList = productService.getProductByUser(user);
        System.out.println(userId);

        modelAndView.addObject("products", productList);
        modelAndView.addObject("user", user);


        return modelAndView;
    }


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping(path = "/orderProduct")
    public ModelAndView orderProduct(@RequestParam("email") String email,
                                     @RequestParam("product") String selectedProduct) {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = productService.getAllAvailableProduct();
        if (productList.isEmpty()) modelAndView.setViewName("productForm");
        if (userService.isNewUserService(email)) {
            modelAndView.addObject("emailError", "The email you entered is not registered. please register first!");
            modelAndView.setViewName("buyingForm");
            modelAndView.addObject("availableProductList", productList);
            return modelAndView;
        }

        User user = userService.getUserByEmail(email);
        int productId = Integer.parseInt(selectedProduct.split("\\s+")[0]);
        Product boughtProduct = productService.getProductById(productId);
        boughtProduct.setStatus((byte) 2);

        if (user == null) {
            System.out.println("Wrong Email");
        } else {
            boughtProduct.setUser(user);
            List<Product> productsList = user.getProducts();
            productsList.add(boughtProduct);
            productService.saveProduct(boughtProduct);

        }
        modelAndView.setViewName("home");


        return modelAndView;

    }
}
