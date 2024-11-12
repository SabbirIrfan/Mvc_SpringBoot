package com.dsi.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.security.Principal;
import java.util.List;


@Setter
@Entity
public class Seller {
    @ModelAttribute
    public void getPrincipal(Principal principal, Model model) {
        System.out.println("hi from seller controller");
        model.addAttribute("principal", principal);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Getter
    @NotNull
    @NotBlank
    @Column(unique = true)
    @Email( message = "wrong Email")
    private String email;

    @Getter
    private String name;


    public Seller() {
    }

    public Seller(Integer id, String email) {
        this.id = id;
        this.email = email;

    }

    public Seller( String email) {
        this.email = email;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", email='" + email + '\'' +

                "," +
                '}';
    }
}
