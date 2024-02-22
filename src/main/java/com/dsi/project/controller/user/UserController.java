package com.dsi.project.controller.user;
import com.dsi.project.model.Product;
import com.dsi.project.model.User;
import com.dsi.project.service.ProductService;
import com.dsi.project.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class UserController {
//    UserService userService;
//    ProductService productService;
//
//    public UserController(UserService userService, ProductService productService) {
//
//        this.userService = userService;
//        this.productService = productService;
//    }
//
//    @GetMapping("/userRegForm")
//    public ModelAndView addUser(Model model){
//        model.addAttribute("user",new User());
//        ModelAndView modelAndView = new ModelAndView("userRegForm");
//
//        return modelAndView;
//    }
//
//
//    @GetMapping("/showUsers")
//    public ModelAndView showUsers(){
//        ModelAndView modelAndView = new ModelAndView("showUsers");
//
//        Iterable<User> users = userService.getAllUserService();
//
//        modelAndView.addObject("users", users);
//        return modelAndView;
//    }
//    @GetMapping("/buyProduct")
//    public ModelAndView buyProduct() {
//        ModelAndView modelAndView = new ModelAndView();
//        System.out.println("hheello");
//        List<Product> productList = productService.getAllAvailableProduct();
//        modelAndView.addObject("availableProductList", productList);
//        modelAndView.setViewName("buyingForm.html");
//        return modelAndView;
//    }
//
//    @PostMapping("/boughtProduct")
//    public ModelAndView boughtProduct(@Param("id") Integer userId){
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
//    @PostMapping("/editUser1") // need to fix this ambiguity :: maybe with a editing view
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
//        System.out.println(user);
//
//        userService.saveUserService(user);
//        return modelAndView;
//
//
//    }
//    @PostMapping("/editUser")
//    public ModelAndView editUser(@ModelAttribute User user){
//        ModelAndView modelAndView = new ModelAndView("home");
//
//        System.out.println(user);
//
//        userService.updateUserService(user);
//        return modelAndView;
//
//
//    }
//    @PostMapping(path = "/orderProduct" )
//    public ModelAndView orderProduct (@RequestParam("email") String email,
//                                      @RequestParam("product") String selectedProduct) {
//        ModelAndView modelAndView = new ModelAndView();
//        List<Product> productList = productService.getAllAvailableProduct();
//        if(productList.isEmpty()) modelAndView.setViewName("productForm");
//        if(userService.isNewUserService(email)){
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
}
