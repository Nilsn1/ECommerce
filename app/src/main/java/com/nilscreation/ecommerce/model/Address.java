package com.nilscreation.ecommerce.model;

import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("city")
    private String city;
    @SerializedName("zipcode")
    private String zipcode;

    public Address(String city, String zipcode) {
        this.city = city;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
