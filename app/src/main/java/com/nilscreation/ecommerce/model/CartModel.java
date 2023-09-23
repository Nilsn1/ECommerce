package com.nilscreation.ecommerce.model;

public class CartModel {
    private int orderId;
    private String title;
    private Float price;
    private int qty;
    private Float itemTotalPrice;
    private Float finalPrice;
    private String image;

    public CartModel(int orderId, String title, Float price, int qty, Float itemTotalPrice, Float finalPrice, String image) {
        this.orderId = orderId;
        this.title = title;
        this.price = price;
        this.qty = qty;
        this.itemTotalPrice = itemTotalPrice;
        this.finalPrice = finalPrice;
        this.image = image;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Float getItemTotalPrice() {
        return itemTotalPrice;
    }

    public void setItemTotalPrice(Float itemTotalPrice) {
        this.itemTotalPrice = itemTotalPrice;
    }

    public Float getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Float finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
