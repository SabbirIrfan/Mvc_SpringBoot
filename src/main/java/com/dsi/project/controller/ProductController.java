package com.dsi.project.controller;


import com.dsi.project.model.Product;
import com.dsi.project.model.User;
import com.dsi.project.service.ProductService;
import com.dsi.project.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/addproduct", method = RequestMethod.POST)
    public ModelAndView addProduct(@ModelAttribute("product") Product product ){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("productForm");
        productService.saveProduct(product);
        System.out.println(product);
        return modelAndView;


    }


    @Transactional
    @RequestMapping(path = "/savenoteoldway" , method = RequestMethod.POST)
    public ModelAndView saveNoteOld(@RequestParam("email") String email,
                                    @RequestParam("product") String selectedProduct,
                                    @RequestParam("detail") String detail){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("got here");

        int productId = Integer.parseInt(selectedProduct.split("\\s+")[0]);

        Product boughtProduct = productService.getProductById(productId);
        boughtProduct.setSold(true);


        modelAndView.addObject("status", "succesfully got the form data?");
        User user = null;
        List<User> userList = userService.getUserByEmail(email);
        if(userList.isEmpty()){
            System.out.println("Wrong Email");
        }
        else{
//            user = userList.get(0);
//            boughtProduct.setUser(user);
//            List<Optional<Product>> productsList = user.getProducts();
//            productsList.add(boughtProduct);
//            userService.saveUserService(user);


//            productService.saveProduct(boughtProduct);

        }



        modelAndView.setViewName("addProduct");


        System.out.println("I am here");
        return modelAndView;

    }



//    @RequestMapping(path = "/savenote" , method = RequestMethod.POST , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public  ModelAndView saveNoteNewWay(@ModelAttribute Product product,@ModelAttribute User user, Model model){
//        // field name of the ModelAttribute and
//        ModelAndView modelAndView = new ModelAndView();
//
//        modelAndView.addObject("status", "succesfully got the form data?");
//        if(userService.isNewUser(user.getId())){
//           product.setUser(user);
//            List<Product> list = user.getProducts();
//            list.add(product);
//            user.setProducts(list);
//            userService.updateUser(user);
//
////            productService.saveProduct(product);
//        }
//        else{
//
//            List<Product> productList = user.getProducts();
//            productList.add(product);
//            product.setUser(user);
//
//            user.setProducts(productList);
//
//            userService.saveUser(user);
////            productService.saveProduct(product);
//
//
//        }
//
//
//        modelAndView.setViewName("form");
//
//
//        System.out.println("I am here");
//        return modelAndView;
//    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
