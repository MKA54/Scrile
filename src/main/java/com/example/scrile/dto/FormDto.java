package com.example.scrile.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Form")
public class FormDto {
    @Schema(description = "First name")
    private String firstName;
    @Schema(description = "Last name")
    private String lastName;
    @Schema(description = "Email")
    private String email;
    @Schema(description = "Product type")
    private String productType;
    @Schema(description = "Comment")
    private String comment;
    @Schema(description = "Selected product cost")
    private int selectedProductCost;
    @Schema(description = "Amount additional options")
    private int amountAdditionalOptions;
    @Schema(description = "Total price")
    private int totalPrice;

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
