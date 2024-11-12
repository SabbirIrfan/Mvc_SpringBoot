//package com.dsi.project.controller.user;
//
//import com.dsi.project.model.Product;
//import com.dsi.project.model.User;
//import com.dsi.project.service.ProductService;
//import com.dsi.project.service.UserService;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.security.Principal;
//import java.util.List;
//
//@Controller
//public class UserController {
//    UserService userService;
//    ProductService productService;
//
//    @ModelAttribute
//    public void getPrincipal(Principal principal, Model model) {
//        System.out.println("hi from seller");
//        model.addAttribute("principal", principal);
//    }
//    public UserController(UserService userService, ProductService productService) {
//
//        this.userService = userService;
//        this.productService = productService;
//    }
//
////    @GetMapping("/userRegForm")
////    public ModelAndView addUser(Model model){
////        model.addAttribute("user",new User());
////
////        return new ModelAndView("userRegForm");
////    }
//
//
//    @GetMapping("/showUsers")
//    public ModelAndView showUsers(){
//        ModelAndView modelAndView = new ModelAndView("showUsers");
//
//        Iterable<User> users = userService.getAllUsers();
//
//        modelAndView.addObject("users", users);
//        return modelAndView;
//    }
//    @GetMapping("/buyProduct")
//    public ModelAndView buyProduct() {
//        ModelAndView modelAndView = new ModelAndView();
//        System.out.println("buying product"); // TODO: use logger
//        List<Product> productList = productService.getAvailableProduct();
//        modelAndView.addObject("availableProductList", productList);
//        modelAndView.setViewName("buyingForm");
//        return modelAndView;
//    }
//
//    @PostMapping("/boughtProduct")
//    public ModelAndView boughtProduct(@Param("id") Integer userId){
//        return getModelAndView(userId, userService, productService);
//    }
//
//    static ModelAndView getModelAndView(@Param("id") Integer userId, UserService userService, ProductService productService) {
//        ModelAndView modelAndView = new ModelAndView("boughtProduct");
//        User user = userService.getUserById(userId);
//        List<Product> productList =  productService.getProductByUser(user);
//        System.out.println(userId);
//
//        modelAndView.addObject("products",productList);
//        modelAndView.addObject("user",user);
//
//
//        return modelAndView;
//    }
//
//
//    @PostMapping("/editUser1") // need to fix this ambiguity :: maybe with common editing view
//    public ModelAndView editUser(@Param("userid") Integer userId){
//        ModelAndView modelAndView = new ModelAndView("editUser");
//        User user = userService.getUserById(userId);
//        modelAndView.addObject("user",user);
//        return modelAndView;
//    }
//    @PostMapping("/addUser")
//    public ModelAndView addUser(@ModelAttribute User user){
//        ModelAndView modelAndView = new ModelAndView("home");
//
//        System.out.println(user);  // TODO: use logger
//        userService.saveUser(user);
//        return modelAndView;
//
//
//    }
//    @PostMapping("/editUser")
//    public ModelAndView editUser(@ModelAttribute User user){
//        ModelAndView modelAndView = new ModelAndView("home");
//
//        System.out.println(user); // TODO: use logger
//
//        userService.updateUser(user);
//        return modelAndView;
//
//
//    }
//    @PostMapping(path = "/orderProduct" )
//    public ModelAndView orderProduct (@RequestParam("email") String email,
//                                      @RequestParam("product") String selectedProduct) {
//        ModelAndView modelAndView = new ModelAndView();
//        List<Product> productList = productService.getAvailableProduct();
//        if(productList.isEmpty()) modelAndView.setViewName("productForm");
//        if(userService.isNewUser(email)){
//            modelAndView.addObject("emailError","The email you entered is not registered. please register first!");
//            modelAndView.setViewName("buyingForm");
//            modelAndView.addObject("availableProductList", productList);
//            return  modelAndView;
//        }
//
//        User user = userService.getUserByEmail(email);
//        int productId = Integer.parseInt(selectedProduct.split("\\s+")[0]);
//        Product boughtProduct = productService.getProductById(productId);
//        boughtProduct.setStatus((byte)2);
//
//        if(user == null) {
//            System.out.println("Wrong Email");
//        }
//        else {
//            boughtProduct.setUser(user);
//            List<Product> productsList = user.getProducts();
//            productsList.add(boughtProduct);
//            productService.saveProduct(boughtProduct);
//
//        }
//        modelAndView.setViewName("home");
//
//
//        return modelAndView;
//
//    }
//}
