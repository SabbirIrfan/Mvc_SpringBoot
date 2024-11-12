//package com.dsi.project.controller.seller;
//
//import com.dsi.project.helper.FileUpload;
//import com.dsi.project.model.Product;
//import com.dsi.project.model.Seller;
//
//import com.dsi.project.service.ProductService;
//import com.dsi.project.service.SellerService;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.repository.query.Param;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.security.Principal;
//import java.util.List;
//
//@Controller
//@RequestMapping("/seller")
//public class SellerController {
//
//    @ModelAttribute
//    public void getPrincipal(Principal principal, Model model) {
//        System.out.println("hi from seller");
//        model.addAttribute("principal", principal);
//    }
//
//    //    @Autowired
//    SellerService sellerService;
//    FileUpload fileUpload;
//
//    ProductService productService;
//
//    public SellerController(SellerService sellerService, ProductService productService, FileUpload fileUpload) {
//        this.sellerService = sellerService;
//        this.productService = productService;
//        this.fileUpload = fileUpload;
//    }
//
//
//    @PreAuthorize("hasAnyRole('SELLER','ADMIN')")
//    @GetMapping("/editSeller")
//    public ModelAndView showEditsellerForm(@Param("sellerId") int sellerId, Model model) {
//        ModelAndView modelAndView = new ModelAndView("editSeller");
//        model.addAttribute("seller", new Seller());
//        Seller seller = sellerService.getSellerById(sellerId);
//        modelAndView.addObject("seller", seller);
//
//        return modelAndView;
//    }
//    @PreAuthorize("hasAnyRole('SELLER','ADMIN')")
//    @PostMapping("/editSeller")
//    public ModelAndView editSeller(Model model,
//                                    @ModelAttribute Seller seller,
//                                   @Param("sellerId") Integer sellerId,
//                                   @Param("name") String name,
//                                   @Param("file") MultipartFile file
//    ){
//        ModelAndView modelAndView = new ModelAndView("sellerProfile");
//        System.out.println(seller.toString());
//        seller.setId(sellerId);
//
//        sellerService.updateSellerService(seller, sellerId);
//        modelAndView.addObject("seller",seller);
//
//
//
//        try {
//            boolean uploadResult = fileUpload.uploadFile(file,sellerId,"seller");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        return modelAndView;
//
//    }
//
//
//
//    @PreAuthorize("hasAnyRole('ADMIN','SELLER')")
//    @GetMapping("/profile")
//    public ModelAndView showProfile(Model model) {
//        System.out.println("hi from profile of seller");
//        ModelAndView modelAndView = new ModelAndView("sellerProfile");
//
//        Principal principal = (Principal) model.getAttribute("principal");
//
//        assert principal != null;
//        String sellerMail = principal.getName();
//
//        Seller seller =  sellerService.getSellerByEmail(sellerMail);
//        modelAndView.addObject("seller",seller);
//        return modelAndView;
//    }
//
//
//
//    @PreAuthorize("hasAnyRole('SELLER','ADMIN')")
//    @GetMapping(value = "/showSellers")
//    public ModelAndView showSeller() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("showSellers");
//        List<Seller> sellerList = sellerService.getAllSellerService();
//        modelAndView.addObject("sellers", sellerList);
//        return modelAndView;
//    }
//
//    @PreAuthorize("hasAnyRole('SELLER','ADMIN')")
//    @GetMapping(value = "/products")
//    public ModelAndView showMyProduct(
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "size", defaultValue = "9") int size,
//            @RequestParam("sellerId") Integer sellerId) {
//        Pageable pageable = PageRequest.of(page, size);
//        ModelAndView modelAndView = new ModelAndView();
//        Seller seller = sellerService.getSellerById(sellerId);
//        modelAndView.addObject("seller", seller);
//
//        Page<Product> productPage = productService.getProductsBySeller(pageable, sellerId);
//
//        modelAndView.addObject("products", productPage.getContent());
//        modelAndView.addObject("totalPages", productPage.getTotalPages());
//        modelAndView.addObject("currentPage", page);
//        modelAndView.addObject("sellerId", sellerId);
//        modelAndView.setViewName("producedProduct");
//        return modelAndView;
//    }
//}
