package com.dsi.project.controller.product;

import com.dsi.project.helper.FileUpload;
import java.security.Principal;
import com.dsi.project.model.User;
import com.dsi.project.service.UserService;
import org.springframework.ui.Model;
import com.dsi.project.model.Product;
import com.dsi.project.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;


@Controller
public class ProductController {

    @ModelAttribute
    public void getPrincipal(Principal principal, Model model) {
        model.addAttribute("principal", principal);
    }
    final private ProductService productService;
    final private UserService userService;
    final private FileUpload fileUpload;

    public ProductController(ProductService productService, UserService userService,FileUpload fileUpload) {

        this.productService = productService;
        this.userService = userService;
        this.fileUpload = fileUpload;
    }

    @PreAuthorize("hasAnyRole('ADMIN','SELLER')")
    @GetMapping(value = "/user/addProduct")
    public ModelAndView addProduct(){

        return new ModelAndView("productForm");
    }

    @PreAuthorize("hasAnyRole('ADMIN','SELLER')")
    @PostMapping(value = "/user/addProduct")
    public ModelAndView addProduct(@ModelAttribute Product product,
                                   @Param("file") MultipartFile file,
                                   @Param("userEmail") String userEmail) {

        ModelAndView modelAndView = new ModelAndView();

        User user = userService.getUserByEmail(userEmail);
        product.setSeller(user);
        productService.saveProduct(product);

        try {
            boolean uploadResult = fileUpload.uploadFile(file,product.getId(),"product");
            System.err.println(uploadResult);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        modelAndView.setViewName("productForm");

        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/setStatus")
    public String setStatus(@RequestParam("productId") int productId,
                            @RequestParam byte status ){

        Product product = productService.getProductById(productId);
        product.setStatus(status);
        productService.saveProduct(product);

        return  "redirect:/showSellers";

    }
}
