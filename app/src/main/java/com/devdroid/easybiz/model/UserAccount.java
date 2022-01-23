package com.devdroid.easybiz.model;

public class UserAccount {

    private String image_url;
    private Long total_pending_deliveries;
    private Long total_accepted_deliveries;
    private Long total_payment_received;
    private Long total_payment_to_be_received;

    public UserAccount(String image_url, Long total_pending_deliveries, Long total_accepted_deliveries,
                       Long total_payment_received, Long total_payment_to_be_received) {
        this.image_url = image_url;
        this.total_pending_deliveries = total_pending_deliveries;
        this.total_accepted_deliveries = total_accepted_deliveries;
        this.total_payment_received = total_payment_received;
        this.total_payment_to_be_received = total_payment_to_be_received;
    }

    public String getImage_url() {
        return image_url;
    }

    public UserAccount setImage_url(String image_url) {
        this.image_url = image_url;
        return this;
    }

    public Long getTotal_pending_deliveries() {
        return total_pending_deliveries;
    }

    public UserAccount setTotal_pending_deliveries(Long total_pending_deliveries) {
        this.total_pending_deliveries = total_pending_deliveries;
        return this;
    }

    public Long getTotal_accepted_deliveries() {
        return total_accepted_deliveries;
    }

    public UserAccount setTotal_accepted_deliveries(Long total_accepted_deliveries) {
        this.total_accepted_deliveries = total_accepted_deliveries;
        return this;
    }

    public Long getTotal_payment_received() {
        return total_payment_received;
    }

    public UserAccount setTotal_payment_received(Long total_payment_received) {
        this.total_payment_received = total_payment_received;
        return this;
    }

    public Long getTotal_payment_to_be_received() {
        return total_payment_to_be_received;
    }

    public UserAccount setTotal_payment_to_be_received(Long total_payment_to_be_received) {
        this.total_payment_to_be_received = total_payment_to_be_received;
        return this;
    }
}
