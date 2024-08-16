package com.dsi.project.controller.seller;

import com.dsi.project.helper.FileUpload;
import com.dsi.project.model.Product;
import com.dsi.project.model.Seller;
import com.dsi.project.model.User;
import com.dsi.project.service.ProductService;
import com.dsi.project.service.SellerService;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @ModelAttribute
    public void getPrincipal(Principal principal, Model model) {
        System.out.println("hi from seller");
        model.addAttribute("principal", principal);
    }

    //    @Autowired
    SellerService sellerService;
    FileUpload fileUpload;

    ProductService productService;

    public SellerController(SellerService sellerService, ProductService productService, FileUpload fileUpload) {
        this.sellerService = sellerService;
        this.productService = productService;
        this.fileUpload = fileUpload;
    }


    @PreAuthorize("hasAnyRole('SELLER','ADMIN')")
    @PostMapping("/editSellerForm")
    public ModelAndView showEditsellerForm(@Param("sellerId") int sellerId, Model model) {
        ModelAndView modelAndView = new ModelAndView("editSeller");
        model.addAttribute("seller", new Seller());
        Seller seller = sellerService.getSellerById(sellerId);
        modelAndView.addObject("seller", seller);

        return modelAndView;
    }
    @PreAuthorize("hasAnyRole('SELLER','ADMIN')")
    @PostMapping("/editSeller")
    public ModelAndView editSeller(@ModelAttribute Seller seller,
                                   @Param("sellerId") Integer sellerId,
                                   @Param("name") String name,
                                   @Param("file") MultipartFile file
    ){
        ModelAndView modelAndView = new ModelAndView("home");
        System.out.println(seller.toString());

        sellerService.updateSellerService(seller, sellerId);
        System.out.println(seller);
        List<Product> productList = productService.getAllAvailableProduct();
        modelAndView.addObject("productList", productList);

        try {
            boolean uploadResult = fileUpload.uploadFile(file,sellerId,"seller");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return modelAndView;

    }



    @PreAuthorize("hasAnyRole('ADMIN','SELLER')")
    @GetMapping("/profile")
    public ModelAndView showProfile(Model model) {
        ModelAndView modelAndView = new ModelAndView("sellerProfile");

        Principal principal = (Principal) model.getAttribute("principal");

        assert principal != null;
        String sellerMail = principal.getName();

        Seller seller =  sellerService.getSellerByEmail(sellerMail);
        modelAndView.addObject("seller",seller);


        return modelAndView;
    }



    @PreAuthorize("hasAnyRole('SELLER','ADMIN')")
    @GetMapping(value = "/showSellers")
    public ModelAndView showSeller() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showSellers");
        List<Seller> sellerList = sellerService.getAllSellerService();
        modelAndView.addObject("sellers", sellerList);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('SELLER','ADMIN')")
    @PostMapping(value = "/producedProduct")
    public ModelAndView showMyProduct(@RequestParam("email") String email) {
        ModelAndView modelAndView = new ModelAndView();
        Seller seller = sellerService.getSellerByEmail(email);
        modelAndView.addObject("seller", seller);
        List<Product> productList = seller.getProducts();
        modelAndView.addObject("products", productList);
        modelAndView.setViewName("producedProduct");
        return modelAndView;
    }
}
