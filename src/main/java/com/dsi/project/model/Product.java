package com.dsi.project.model;


import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

//    private int sellerId;


    private String brandName;
    private String productModel;

    private String productDetail;

    private String processor;
    private String generation;

    private byte status;

    @ManyToOne(fetch = FetchType.EAGER)
    private Seller seller;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    public Product() {
        this.status = 0 ;
    }

    public Product(String brandName, String productModel, String productDetail, String processor, String generation) {
        this.brandName = brandName;
        this.productModel = productModel;
        this.productDetail = productDetail;
        this.processor = processor;
        this.generation = generation;
        this.status = 0;

    }

    public Product(int id, String brandName, String productModel, String productDetail, String processor, String generation) {
        this.id = id;
        this.brandName = brandName;
        this.productModel = productModel;
        this.productDetail = productDetail;
        this.processor = processor;
        this.generation = generation;
        this.status = 0;

    }

    public Product(String brandName, String productDetail, Seller seller) {
        this.status = 0;
        this.brandName = brandName;
        this.productDetail = productDetail;
        this.seller = seller;

    }



    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }


    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String model) {
        this.productModel = model;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
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
                ", seller=" + seller +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
