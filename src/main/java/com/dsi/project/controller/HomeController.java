package com.dsi.project.controller;

import org.springframework.ui.Model;
import com.dsi.project.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.dsi.project.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    /**
     * Adds the Principal object to the model to be accessible across all views.
     */
    @ModelAttribute
    public void addPrincipalToModel(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("principal", principal);
        }
    }

    /**
     * Renders the home page with paginated products.
     *
     * @param page      Current page number, default is 0.
     * @param size      Number of products per page, default is 9.
     * @return          ModelAndView with paginated product list.
     */
    @GetMapping("/home")
    public ModelAndView home(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "9") int size) {

        return getPaginatedProducts(page, size, null);
    }

    /**
     * Renders the home page with paginated products.
     *
     * @param page      Current page number, default is 0.
     * @param size      Number of products per page, default is 9.
     * @return          ModelAndView with paginated product list.
     */
    @GetMapping("/")
    public ModelAndView showProductForm(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "9") int size) {

            return getPaginatedProducts(page, size, null);
        }

    /**
     * Handles product search with pagination.
     *
     * @param page      Current page number, default is 0.
     * @param size      Number of products per page, default is 9.
     * @param query     Search query string.
     * @return          ModelAndView with paginated search results.
     */
    @GetMapping("/search")
    public ModelAndView search(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "9") int size,
            @RequestParam(value = "query") String query) {

        return getPaginatedProducts(page, size, query);
    }

    /**
     * Displays the custom login page.
     *
     * @param model     Model to add attributes to.
     * @return          ModelAndView for login page.
     */
    @GetMapping("/signin")
    public ModelAndView customLogin(Model model) {
        return new ModelAndView("login");
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
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage;

        if (query != null && !query.trim().isEmpty()) {
            productPage = productService.getSearchedProduct(pageable, query);
        } else {
            productPage = productService.getAllAvailableProduct(pageable);
        }

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("productList", productPage.getContent());
        modelAndView.addObject("totalPages", productPage.getTotalPages());
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("query", query);

        return modelAndView;
    }
}
