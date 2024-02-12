package com.dsi.project.controller;


import com.dsi.project.model.Product;
import com.dsi.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();

        List<Product> productList = productService.getAllProduct();
        modelAndView.addObject("productList",productList);
        modelAndView.setViewName("home.html");

        return modelAndView;
    }


}
