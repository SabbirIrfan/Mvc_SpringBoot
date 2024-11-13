package com.dsi.project.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dsi.project.helper.FileUpload;
import com.dsi.project.model.User;
import com.dsi.project.model.Product;
import org.springframework.ui.Model;
import com.dsi.project.service.ProductService;
import com.dsi.project.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Controller class for handling user profile-related operations.
 */
@Controller
@RequestMapping("/user")
public class ProfileController {

    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
    private final FileUpload fileUpload;
    private final UserService userService;
    private final ProductService productService;


    /**
     * Constructor for ProfileController with required services.
     *
     * @param fileUpload      Helper class for file upload operations
     * @param userService     Service for user-related operations
     * @param productService  Service for product-related operation
     */
    public ProfileController(FileUpload fileUpload, UserService userService,
                             ProductService productService) {
        this.fileUpload = fileUpload;
        this.userService = userService;
        this.productService = productService;
    }

    /**
     * Adds the logged-in user's Principal to the model.
     *
     * @param principal The logged-in user's principal
     * @param model     The model to add attributes
     */
    @ModelAttribute
    public void getPrincipal(Principal principal, Model model) {
        model.addAttribute("principal", principal);
    }

    /**
     * Displays a list of all users.
     *
     * @return ModelAndView for showing users
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/showUsers")
    public ModelAndView showUsers() {
        ModelAndView modelAndView = new ModelAndView("showUsers");
        Iterable<User> users = userService.getUsers("ROLE_USER");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    /**
     * Displays a list of all users.
     *
     * @return ModelAndView for showing users
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/showSellers")
    public ModelAndView showSellers() {
        ModelAndView modelAndView = new ModelAndView("showUsers");
        Iterable<User> users = userService.getUsers("ROLE_SELLER");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    /**
     * Displays the profile of the logged-in user.
     *
     * @param model The model to get attributes
     * @return ModelAndView for user profile view
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'SELLER')")
    @GetMapping("/profile")
    public ModelAndView showUserProfile(Model model) {
        Principal principal = (Principal) model.getAttribute("principal");
        if (principal == null) {
            logger.info("No principle?");
            System.out.println("No principle? :");
            return new ModelAndView("redirect:/signin");
        }

        ModelAndView modelAndView = new ModelAndView("userProfile");
        User user = userService.getUserByEmail(principal.getName());
        logger.info("Got user in user profile with roletype :" + user.getRoles());
        System.out.println("Got user in user profile with roletype :" + user.getRoles());
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    /**
     * Displays the form for editing user information.
     *
     * @param userId The ID of the user to be edited
     * @return ModelAndView for edit user form view
     */
    @PreAuthorize("hasAnyRole('USER','ADMIN','SELLER')")
    @GetMapping("/editUser")
    public ModelAndView showEditUser(@Param("userId") Integer userId) {
        ModelAndView modelAndView = new ModelAndView("editUser");
        User user = userService.getUserById(userId);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    /**
     * Handles the user update operation.
     *
     * @param model  The model to update attributes
     * @param user   User entity containing updated data
     * @param userId The ID of the user to be updated
     * @param name   Updated name of the user
     * @param file   Multipart file for profile picture upload
     * @return ModelAndView for the home page after update
     */
    @PreAuthorize("hasAnyRole('USER','ADMIN','SELLER')")
    @PostMapping("/editUser")
    public ModelAndView editUser(Model model,
                                 @ModelAttribute User user,
                                 @Param("userId") int userId,
                                 @Param("name") String name,
                                 @Param("file") MultipartFile file) {

        // Update user's name
        System.out.println(user+ "::: found user");
        System.out.println(userId+ "::: found userId");
        user.setId(userId);
        user.setName(name);
        userService.updateUser(user);

        // Fetch products for the home page
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.getAvailableProduct(pageable);

        model.addAttribute("productList", productPage.getContent());
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("currentPage", page);

        // Handle file upload (user profile picture)
        try {
            fileUpload.uploadFile(file, userId,"user");
        } catch (Exception e) {
            throw new RuntimeException("File upload failed: " + e.getMessage(), e);
        }

        return new ModelAndView("home");
    }
}
