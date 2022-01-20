package com.devdroid.easybiz.model;

public class Gift {
    private String product_name;
    private String product_description;
    private String long_product_description;
    private Long items_remaining;
    private String product_price;
    private String image_url;


    public Gift()
    {

    }

    public String getProduct_name() {
        return product_name;
    }

    public Gift setProduct_name(String product_name) {
        this.product_name = product_name;
        return this;
    }

    public String getProduct_description() {
        return product_description;
    }

    public Gift setProduct_description(String product_description) {
        this.product_description = product_description;
        return this;
    }

    public String getLong_product_description() {
        return long_product_description;
    }

    public Gift setLong_product_description(String long_product_description) {
        this.long_product_description = long_product_description;
        return this;
    }

    public Long getItems_remaining() {
        return items_remaining;
    }

    public Gift setItems_remaining(Long items_remaining) {
        this.items_remaining = items_remaining;
        return this;
    }

    public String getProduct_price() {
        return product_price;
    }

    public Gift setProduct_price(String product_price) {
        this.product_price = product_price;
        return this;
    }

    public String getImage_url() {
        return image_url;
    }

    public Gift setImage_url(String image_url) {
        this.image_url = image_url;
        return this;
    }
}
