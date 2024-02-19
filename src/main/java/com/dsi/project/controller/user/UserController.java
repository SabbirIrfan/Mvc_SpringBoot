package com.dsi.project.controller.user;

import com.dsi.project.model.Product;
import com.dsi.project.model.User;
import com.dsi.project.service.ProductService;
import com.dsi.project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class UserController {
    UserService userService;
    ProductService productService;

    public UserController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }


    @GetMapping("/showUsers")
    public ModelAndView showUsers(){

        return new ModelAndView("userProfile");
    }
    @GetMapping("/buyProduct")
    public ModelAndView buyProduct() {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("hheello");
        List<Product> productList = productService.getAllAvailableProduct();
        modelAndView.addObject("availableProductList", productList);
        modelAndView.setViewName("buyingForm.html");
        return modelAndView;
    }

    @PostMapping(path = "/orderProduct" )
    public ModelAndView orderProduct (@RequestParam("email") String email,
                                      @RequestParam("product") String selectedProduct) {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = productService.getAllAvailableProduct();
        if(productList.isEmpty()) modelAndView.setViewName("productForm");
        if(userService.isNewUserService(email)){
            modelAndView.addObject("emailError","The email you entered is not registered. please register first!");
            modelAndView.setViewName("buyingForm");
            modelAndView.addObject("availableProductList", productList);
            return  modelAndView;
        }

        User user = userService.getUserByEmail(email);
        int productId = Integer.parseInt(selectedProduct.split("\\s+")[0]);
        Product boughtProduct = productService.getProductById(productId);
        boughtProduct.setStatus((byte)2);

        if(user == null) {
            System.out.println("Wrong Email");
        }
        else {
            boughtProduct.setUser(user);
            List<Product> productsList = user.getProducts();
            productsList.add(boughtProduct);
            productService.saveProduct(boughtProduct);

        }
        modelAndView.setViewName("home");


        return modelAndView;

    }
}
