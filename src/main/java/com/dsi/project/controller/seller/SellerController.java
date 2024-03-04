package com.dsi.project.controller.seller;

import com.dsi.project.model.Product;
import com.dsi.project.model.Seller;
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
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @ModelAttribute
    public void getPrincipal(Principal principal, Model model){
        System.out.println("hi from seller");
        model.addAttribute("principal", principal);
    }

//    @Autowired
    SellerService sellerService;

    ProductService productService;

    public SellerController(SellerService sellerService, ProductService productService) {
        this.sellerService = sellerService;
        this.productService = productService;
    }

    @PreAuthorize("hasAnyRole('SELLER','ADMIN')")
    @PostMapping(value = "/addSeller")
    public String addSeller(@Valid  @ModelAttribute("seller") Seller seller, BindingResult result) {

        if(result.hasErrors()){ // this will not get  sql multiple key error
            return "sellerForm";
        }
        if(sellerService.isNewSellerService(seller.getEmail())){
            result.addError(new FieldError("seller", "email", "this email already has an account!"));
            return "sellerForm";
        }
        sellerService.saveSellerService(seller);

        return "sellerForm";

    }


    @PreAuthorize("hasAnyRole('SELLER','ADMIN')")
    @GetMapping(value = "/serllerRegForm")
    public ModelAndView sellerForm(Model model){
        model.addAttribute("seller",new Seller());
        return new ModelAndView("sellerForm");
    }

    @PreAuthorize("hasAnyRole('SELLER','ADMIN')")
    @PostMapping("/editSellerForm")
    public ModelAndView showEditsellerForm(@Param("sellerId") int sellerId, Model model) {
        ModelAndView modelAndView = new ModelAndView("editSeller");
        model.addAttribute("seller",new Seller());
        Seller seller = sellerService.getSellerById(sellerId);
        modelAndView.addObject("seller",seller);


        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('SELLER','ADMIN')")
    @PostMapping("/editSeller")
    public ModelAndView editSeller(@ModelAttribute Seller seller, @Param("sellerId") int sellerId){
        ModelAndView modelAndView = new ModelAndView("home");

        sellerService.updateSellerService(seller,sellerId);

        List<Product> productList = productService.getAllAvailableProduct();
        modelAndView.addObject("productList",productList);

        return  modelAndView;

    }
    @PreAuthorize("hasAnyRole('SELLER','ADMIN')")
    @GetMapping(value = "/showSellers")
    public ModelAndView showSeller(){
        ModelAndView modelAndView  =  new ModelAndView();
        modelAndView.setViewName("showSellers");
        List<Seller> sellerList = sellerService.getAllSellerService();
        modelAndView.addObject("sellers", sellerList);
        return  modelAndView;
    }
    @PreAuthorize("hasAnyRole('SELLER','ADMIN')")
    @PostMapping(value = "/producedProduct")
    public ModelAndView showMyProduct(@RequestParam("email") String email){
        ModelAndView modelAndView = new ModelAndView();
        Seller seller = sellerService.getSellerByEmail(email).getFirst();
        modelAndView.addObject("seller", seller);
        List<Product> productList  = seller.getProducts();
        modelAndView.addObject("products", productList);
        modelAndView.setViewName("producedProduct");
        return modelAndView;
    }
}
