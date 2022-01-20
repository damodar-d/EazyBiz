package com.devdroid.easybiz.model;

public class Pending {

    private String name;
    private String amount_to_paid;
    private String mode_of_transaction;
    private String image_url;
    private String date_of_delivery;


    public Pending()
    {

    }

    public String getName() {
        return name;
    }

    public Pending setName(String name) {
        this.name = name;
        return this;
    }

    public String getAmount_to_paid() {
        return amount_to_paid;
    }

    public Pending setAmount_to_paid(String amount_to_paid) {
        this.amount_to_paid = amount_to_paid;
        return this;
    }

    public String getMode_of_transaction() {
        return mode_of_transaction;
    }

    public Pending setMode_of_transaction(String mode_of_transaction) {
        this.mode_of_transaction = mode_of_transaction;
        return this;
    }

    public String getImage_url() {
        return image_url;
    }

    public Pending setImage_url(String image_url) {
        this.image_url = image_url;
        return this;
    }

    public String getDate_of_delivery() {
        return date_of_delivery;
    }

    public Pending setDate_of_delivery(String date_of_delivery) {
        this.date_of_delivery = date_of_delivery;
        return this;
    }
}
