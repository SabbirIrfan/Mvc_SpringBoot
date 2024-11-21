package com.dsi.project.controller.user;

import com.dsi.project.dto.ProductOrderDTO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dsi.project.model.Product;
import com.dsi.project.model.User;
import com.dsi.project.service.ProductService;
import com.dsi.project.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class OtherController {

    private static final Logger logger = LoggerFactory.getLogger(OtherController.class);
    UserService userService;
    ProductService productService;
    @ModelAttribute
    public void getPrincipal(HttpSession session,Principal principal, Model model){
        System.out.println("hi from other");
        model.addAttribute("principal", principal);

        if(principal != null){
            User user = userService.getUserByEmail(principal.getName());
            session.setAttribute("userId", user.getId());
        }
    }

    public OtherController(UserService userService, ProductService productService) {

        this.userService = userService;
        this.productService = productService;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','SELLER')")
    @GetMapping("/buyProduct/{id}")
    public ModelAndView buyProduct(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Product product = productService.getProductById(id);
        modelAndView.addObject("product", product);
        modelAndView.setViewName("buyingForm");
        return modelAndView;
    }


    @PostMapping(path = "/orderProduct")
    public ModelAndView orderProduct(@Valid @ModelAttribute ProductOrderDTO orderDTO,
                                     BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        boolean orderSuccess = userService.orderProduct(orderDTO.getEmail(), orderDTO.getProductId());


        // Check for validation errors first
        if (bindingResult.hasErrors()) {

            Map<String, String> errors = new HashMap<>();

            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );

            bindingResult.getGlobalErrors().forEach(error ->
                    errors.put(error.getObjectName(), error.getDefaultMessage())
            );


            modelAndView.addObject("errors", errors);

            modelAndView.setViewName("buyingForm");
            return modelAndView;
        }

        if (!orderSuccess) {
            modelAndView.addObject("emailError", "The email you entered is not registered. please register first!");
            modelAndView.setViewName("buyingForm");
            return modelAndView;
        }

        int size = 9;
        int page = 0;
        return getPaginatedProducts(page, size, null);

    }

    @PreAuthorize("hasAnyRole('ADMIN','USER','SELLER')")
    @GetMapping("/products")
    public ModelAndView Product(@RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "9") int size,
                                @RequestParam("userId") Integer userId,
                                Model model) {
        logger.info("Fetching products for user with id : "+ userId);
        ModelAndView modelAndView = new ModelAndView("products");
        Pageable pageable = PageRequest.of(page, size);

        User user = userService.getUserById(userId);
        modelAndView.addObject("user", user);

        Page<Product> productPage = userService.getProductsByUserRole(pageable,userId);


        modelAndView.addObject("products", productPage.getContent());
        modelAndView.addObject("totalPages", productPage.getTotalPages());
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("userId", userId);
        modelAndView.setViewName("products");

        logger.info("Fetched products for user with id : "+ userId);
        return modelAndView;

    }

    /**
     * Helper method to get paginated product list or search results.
     *
     * @param page      Current page number.
     * @param size      Number of products per page.
     * @param query     Search query, null if not searching.
     * @return          ModelAndView with products and pagination details.
     */
    private ModelAndView getPaginatedProducts(int page, int size, String query) {
        return getModelAndView(page, size, query, productService);
    }

    public static ModelAndView getModelAndView(int page, int size, String query, ProductService productService) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage;

        if (query != null && !query.trim().isEmpty()) {
            productPage = productService.getSearchedProduct(pageable, query);
        } else {
            productPage = productService.getAvailableProduct(pageable);
        }

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("productList", productPage.getContent());
        modelAndView.addObject("totalPages", productPage.getTotalPages());
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("query", query);

        return modelAndView;
    }

}
