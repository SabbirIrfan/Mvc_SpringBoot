package com.dsi.project.controller.product;


import com.dsi.project.helper.FileUpload;
import com.dsi.project.model.Product;
import com.dsi.project.model.Seller;
import com.dsi.project.service.ProductService;
import com.dsi.project.service.SellerService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDateTime;





@Controller
public class ProductController {


    @ModelAttribute
    public void getPrincipal(Principal principal, Model model) {
        System.out.println("hi from ProfileController");
        model.addAttribute("principal", principal);
    }
//    @Autowired
    final private ProductService productService;

//    @Autowired
    final private SellerService sellerService;

//    @Autowired
    final private FileUpload fileUpload;


    public ProductController(ProductService productService, SellerService sellerService, FileUpload fileUpload) {
        this.productService = productService;
        this.sellerService = sellerService;
        this.fileUpload = fileUpload;
    }

    @ModelAttribute
    public void setProduct(Model model){

    }
    @GetMapping("/")
    public String showProductForm(Model model){
        String name = "spring mvc configuration!! ";
        model.addAttribute("name", name);

        System.out.println("this is home and the time is "+ LocalDateTime.now());
        // this has a bug in base.html
        return "home";
    }


    @PreAuthorize("hasAnyRole('ADMIN','SELLER')")
    @GetMapping(value = "/seller/productForm")
    public ModelAndView productForm(Model model){
        Principal principal = (Principal) model.getAttribute("principal");
        model.addAttribute("principal", principal);
        System.out.println("in sell product form");
        return new ModelAndView("productForm");

    }
    @PreAuthorize("hasAnyRole('ADMIN','SELLER')")
    @PostMapping(value = "/seller/addproduct")
    public ModelAndView addProduct(@ModelAttribute Product product,
                                   @Param("file") MultipartFile file,
                                   @Param("sellerEmail") String sellerEmail,
                                    Model model) {

        ModelAndView modelAndView = new ModelAndView();
        Principal principal = (Principal) model.getAttribute("principal");
        model.addAttribute("principal", principal);
        Seller seller = sellerService.getSellerByEmail(sellerEmail);
        product.setSeller(seller);
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
