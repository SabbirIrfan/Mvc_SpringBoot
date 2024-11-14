package com.dsi.project.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String brandName;
    private String productModel;
    private String productDetail;
    private String processor;
    private String generation;
    private byte status;
    private Integer quantity;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buyer_id")
    private User buyer;

    public Product() {
        this.status = 0 ;
    }

    public Product(String brandName, String productModel, String productDetail, String processor, String generation, Integer quantity) {
        this.brandName = brandName;
        this.productModel = productModel;
        this.productDetail = productDetail;
        this.processor = processor;
        this.generation = generation;
        this.quantity = quantity;
        this.status = 0;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", productModel='" + productModel + '\'' +
                ", productDetail='" + productDetail + '\'' +
                ", processor='" + processor + '\'' +
                ", generation='" + generation + '\'' +
                ", "+'}';
    }
}
