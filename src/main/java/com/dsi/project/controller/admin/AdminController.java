package com.dsi.project.controller.admin;

import com.dsi.project.model.Product;
import com.dsi.project.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    public ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute
    public void getPrincipal(Principal principal, Model model){
        System.out.println("hi from product");
        model.addAttribute("principal", principal);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/setStatus")
    public String setStatus(@RequestParam("productId") int productId,
                            @RequestParam byte status ){

        Product product = productService.getProductById(productId);
        product.setStatus(status);
        productService.saveProduct(product);

        return  "redirect:/user/showSellers";

    }
}
