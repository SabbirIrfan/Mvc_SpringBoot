package com.dsi.project.controller;


import com.dsi.project.model.Product;
import com.dsi.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @ModelAttribute
    public void getPrincipal(Principal principal, Model model){
        System.out.println("hi from ProfileController");
        model.addAttribute("principal", principal);
    }

    @GetMapping("/home")
    public ModelAndView home(Model model){
        ModelAndView modelAndView = new ModelAndView();
        Principal principal = (Principal) model.getAttribute("principal");
        model.addAttribute("principal", principal);
        List<Product> productList = productService.getAllAvailableProduct();


        modelAndView.addObject("productList",productList);


        return modelAndView;
    }





}
