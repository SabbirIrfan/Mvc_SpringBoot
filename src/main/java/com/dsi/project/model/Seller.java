package com.dsi.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;


@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @NotBlank
    @Column(unique = true)
    @Email( message = "wrong Email")
    private String email;

    private String name;

    @OneToMany(mappedBy ="seller" ,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Product> products;
    public Seller() {
    }

    public Seller(Integer id, String email) {
        this.id = id;
        this.email = email;

    }

    public Seller( String email) {
        this.email = email;

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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

                ", products=" + products +
                '}';
    }
}
