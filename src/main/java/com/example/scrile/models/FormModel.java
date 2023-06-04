package com.example.scrile.models;

import javax.persistence.*;

@Entity
@Table(name = "form")
public class FormModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String email;
    @Column(name = "product_type")
    private String productType;
    @Column
    private String comment;
    @Column(name = "selected_product_cost")
    private int selectedProductCost;
    @Column(name = "amount_additional_options")
    private int amountAdditionalOptions;
    @Column(name = "total_price")
    private int totalPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getSelectedProductCost() {
        return selectedProductCost;
    }

    public void setSelectedProductCost(int selectedProductCost) {
        this.selectedProductCost = selectedProductCost;
    }

    public int getAmountAdditionalOptions() {
        return amountAdditionalOptions;
    }

    public void setAmountAdditionalOptions(int amountAdditionalOptions) {
        this.amountAdditionalOptions = amountAdditionalOptions;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
