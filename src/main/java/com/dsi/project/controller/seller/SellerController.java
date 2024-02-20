package com.dsi.project.controller.seller;

import com.dsi.project.model.Product;
import com.dsi.project.model.Seller;
import com.dsi.project.service.SellerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SellerController {

//    @Autowired
    SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

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



    @GetMapping(value = "/serllerRegForm")
    public ModelAndView sellerForm(Model model){
        model.addAttribute("seller",new Seller());
        return new ModelAndView("sellerForm");
    }

    @GetMapping(value = "/showSellers")
    public ModelAndView showSeller(){
        ModelAndView modelAndView  =  new ModelAndView();
        modelAndView.setViewName("showSellers");
        List<Seller> sellerList = sellerService.getAllSellerService();
        modelAndView.addObject("seller", sellerList);
        return  modelAndView;
    }

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
