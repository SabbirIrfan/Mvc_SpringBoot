package com.dsi.project.controller;

import com.dsi.project.model.Product;
import com.dsi.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @ModelAttribute
    public void getPrincipal(Principal principal, Model model) {
        System.out.println("hi from ProfileController");
        model.addAttribute("principal", principal);
    }

    @GetMapping("/home")
    public ModelAndView home(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "9") int size,
            Principal principal) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("principal", principal);

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.getAllAvailableProduct(pageable);
        modelAndView.addObject("productList", productPage.getContent());
        modelAndView.addObject("totalPages", productPage.getTotalPages());
        modelAndView.addObject("currentPage", page);
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView search(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "9") int size,
            @RequestParam(value = "query") String query,
            Principal principal) {

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("principal", principal);
        Pageable pageable = PageRequest.of(page, size);

        Page<Product> productPage = productService.getSearchedProduct(pageable, query);
       
        modelAndView.addObject("productList", productPage.getContent());
        modelAndView.addObject("totalPages", productPage.getTotalPages());
        modelAndView.addObject("currentPage", page);
        return modelAndView;
    }

    @GetMapping("/signin")
    public ModelAndView customLogin(Model model) {

        ModelAndView modelAndView = new ModelAndView("login");
        Principal principal = (Principal) model.getAttribute("principal");
        model.addAttribute("principal", principal);
        System.out.println(principal);
        return modelAndView;
    }

}
