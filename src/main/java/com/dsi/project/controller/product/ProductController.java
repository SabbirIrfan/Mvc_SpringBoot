package com.dsi.project.controller.product;


import com.dsi.project.helper.FileUpload;
import com.dsi.project.model.Product;
import com.dsi.project.model.User;
import com.dsi.project.service.ProductService;
import com.dsi.project.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;



@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileUpload fileUpload;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/")
    public String showProductForm(Model model){
        String name = "spring mvc configuration!! ";
        model.addAttribute("name", name);

        System.out.println("this is home and the time is "+ LocalDateTime.now());

        return "productForm";
    }

    @GetMapping("/buyproduct")
    public ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = productService.getAllProduct();
        productList.removeIf(Product::isSold);
        modelAndView.addObject("availableProductList", productList);
        modelAndView.setViewName("buyingForm");
        return modelAndView;
    }


    @GetMapping(value = "/productForm")
    public ModelAndView productForm(Model model){
        return new ModelAndView("productForm");

    }
    @PostMapping(value = "/addproduct")
    public ModelAndView addProduct(@ModelAttribute Product product, @RequestParam("file") MultipartFile file){
        ModelAndView modelAndView = new ModelAndView();
        productService.saveProduct(product);

        try {
            boolean uploadResult = fileUpload.uploadFile(file,product.getId());
            System.out.println(uploadResult);

        }catch (Exception e) {
            System.out.println("upload failed");

        }
        modelAndView.setViewName("productForm");
        return modelAndView;

    }



    @PostMapping(path = "/orderProduct" )
    public ModelAndView orderProduct (@RequestParam("email") String email,
                                      @RequestParam("product") String selectedProduct,
                                      @RequestParam("detail") String detail) {
        ModelAndView modelAndView = new ModelAndView();
        User user;
        List<User> userList = userService.getUserByEmail(email);

        int productId = Integer.parseInt(selectedProduct.split("\\s+")[0]);
        Product boughtProduct = productService.getProductById(productId);
        boughtProduct.setSold(true);
        modelAndView.addObject("status", "succesfully got the form data?");

        if(userList.isEmpty()) {
            System.out.println("Wrong Email");
        }
        else {
            user = userList.getFirst();
            boughtProduct.setUser(user);
            List<Product> productsList = user.getProducts();
            productsList.add(boughtProduct);
            productService.saveProduct(boughtProduct);

        }

        modelAndView.setViewName("buyingForm");
        return modelAndView;

    }

}
