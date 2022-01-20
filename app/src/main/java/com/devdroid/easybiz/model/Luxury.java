package com.devdroid.easybiz.model;

public class Luxury {
    private String product_name;
    private String product_description;
    private Long items_remaining;
    private String product_price;
    private String image_url;

    public Luxury()
    {

    }

    public String getProduct_name() {
        return product_name;
    }

    public Luxury setProduct_name(String product_name) {
        this.product_name = product_name;
        return this;
    }

    public String getProduct_description() {
        return product_description;
    }

    public Luxury setProduct_description(String product_description) {
        this.product_description = product_description;
        return this;
    }


    public Long getItems_remaining() {
        return items_remaining;
    }

    public Luxury setItems_remaining(Long items_remaining) {
        this.items_remaining = items_remaining;
        return this;
    }

    public String getProduct_price() {
        return product_price;
    }

    public Luxury setProduct_price(String product_price) {
        this.product_price = product_price;
        return this;
    }

    public String getImage_url() {
        return image_url;
    }

    public Luxury setImage_url(String image_url) {
        this.image_url = image_url;
        return this;
    }
}
