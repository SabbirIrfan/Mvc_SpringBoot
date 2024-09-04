package com.dsi.project.controller;

import com.dsi.project.model.Product;
import com.dsi.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public ModelAndView home(Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "9") int size) {
        ModelAndView modelAndView = new ModelAndView("home");
        Principal principal = (Principal) model.getAttribute("principal");
        model.addAttribute("principal", principal);
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.getAllAvailableProduct(pageable);
        model.addAttribute("productList", productPage.getContent());
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("currentPage", page);

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
