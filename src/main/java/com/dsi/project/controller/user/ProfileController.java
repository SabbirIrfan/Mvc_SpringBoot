package com.dsi.project.controller.user;

import com.dsi.project.helper.FileUpload;
import com.dsi.project.model.User;
import com.dsi.project.model.Product;
import org.springframework.ui.Model;
import com.dsi.project.service.AllUserService;
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

    private final FileUpload fileUpload;
    private final UserService userService;
    private final ProductService productService;
    private final AllUserService allUserService;

    /**
     * Constructor for ProfileController with required services.
     *
     * @param fileUpload      Helper class for file upload operations
     * @param userService     Service for user-related operations
     * @param productService  Service for product-related operations
     * @param allUserService  Service for handling all user operations
     */
    public ProfileController(FileUpload fileUpload, UserService userService,
                             ProductService productService, AllUserService allUserService) {
        this.fileUpload = fileUpload;
        this.userService = userService;
        this.productService = productService;
        this.allUserService = allUserService;
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
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/showUsers")
    public ModelAndView showUsers() {
        ModelAndView modelAndView = new ModelAndView("showUsers");
        Iterable<User> users = userService.getAllUserService();
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    /**
     * Displays the profile of the logged-in user.
     *
     * @param model The model to get attributes
     * @return ModelAndView for user profile view
     */
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/profile")
    public ModelAndView showUserProfile(Model model) {
        Principal principal = (Principal) model.getAttribute("principal");
        if (principal == null) {
            return new ModelAndView("redirect:/signin");
        }

        ModelAndView modelAndView = new ModelAndView("userProfile");
        User user = userService.getUserByEmail(principal.getName());
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    /**
     * Displays the form for editing user information.
     *
     * @param userId The ID of the user to be edited
     * @return ModelAndView for edit user form view
     */
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/editUserForm")
    public ModelAndView showEditUser(@Param("userid") Integer userId) {
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
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/editUser")
    public ModelAndView editUser(Model model,
                                 @ModelAttribute User user,
                                 @Param("userId") int userId,
                                 @Param("name") String name,
                                 @Param("file") MultipartFile file) {

        // Update user's name
        user.setName(name);
        userService.updateUserService(user, userId);

        // Fetch products for the home page
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.getAllAvailableProduct(pageable);

        model.addAttribute("productList", productPage.getContent());
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("currentPage", page);

        // Handle file upload (user profile picture)
        try {
            fileUpload.uploadFile(file, userId, "user");
        } catch (Exception e) {
            throw new RuntimeException("File upload failed: " + e.getMessage(), e);
        }

        return new ModelAndView("home");
    }
}
