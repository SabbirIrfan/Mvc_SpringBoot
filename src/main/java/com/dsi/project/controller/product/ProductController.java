package com.dsi.project.controller.product;


import com.dsi.project.helper.FileUpload;
import com.dsi.project.model.Product;
import com.dsi.project.model.Seller;
import com.dsi.project.service.ProductService;
import com.dsi.project.service.SellerService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;



@Controller
public class ProductController {
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

        return "productForm";
    }



    @GetMapping(value = "/productForm")
    public ModelAndView productForm(Model model){
        return new ModelAndView("productForm");

    }
    @PostMapping(value = "/addproduct")
    public ModelAndView addProduct(@ModelAttribute Product product,
                                   @RequestParam("file") MultipartFile file,
                                   @RequestParam("sellerEmail") String sellerEmail) {
        ModelAndView modelAndView = new ModelAndView();
        Seller seller = sellerService.getSellerByEmail(sellerEmail).getFirst();
        product.setSeller(seller);
        productService.saveProduct(product);
        try {
            boolean uploadResult = fileUpload.uploadFile(file,product.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        modelAndView.setViewName("productForm");
        return modelAndView;

    }


    @PostMapping("/setStatus")
    public String setStatus(@RequestParam("productIdl") int productId,
                            @RequestParam byte status ){

        Product product = productService.getProductById(productId);
        product.setStatus(status);
        productService.saveProduct(product);

        return  "redirect:/showSellers";

    }



}
