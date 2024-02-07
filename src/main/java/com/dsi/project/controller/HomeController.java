package com.dsi.project.controller;


import com.dsi.project.model.Product;
import com.dsi.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @RequestMapping("/")
    public String showProductForm(Model model){
        String name = "spring mvc configuration!! ";
        model.addAttribute("name", name);

        System.out.println("this is home and the time is "+ LocalDateTime.now());

        return "productForm";
    }
    @RequestMapping("/buyproduct")
    public ModelAndView modelAndView(){
         System.out.println("from model view");

        ModelAndView modelAndView = new ModelAndView();

        List<Product> productList = productService.getAllProduct();
        productList.removeIf(Product::isSold);
        System.out.println("the remaining product to show "+ productList);
        modelAndView.addObject("availableProductList", productList);
        modelAndView.setViewName("buyingForm");
        return modelAndView;
    }

    @RequestMapping(value = "/userForm", method = RequestMethod.GET)
    public ModelAndView userForm(Model model){
        ModelAndView modelAndView = new ModelAndView("userForm");
        return modelAndView;

    }
    @RequestMapping(value = "/productForm", method = RequestMethod.GET)
    public ModelAndView productForm(Model model){
        ModelAndView modelAndView = new ModelAndView("productForm");
        return modelAndView;

    }
}
