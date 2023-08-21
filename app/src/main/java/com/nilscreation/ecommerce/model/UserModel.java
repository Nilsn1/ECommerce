package com.nilscreation.ecommerce.model;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("email")
    private String email;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("address")
    private Address address;

    public UserModel() {

    }

    public UserModel(String email, String username, String password, Address address) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
