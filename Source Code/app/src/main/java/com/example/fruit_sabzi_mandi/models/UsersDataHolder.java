package com.example.fruit_sabzi_mandi.models;

public class UsersDataHolder {
    private String Location;
    private String Phone;
    private String shopName;
    private String email;

    public UsersDataHolder(){
    }

    public UsersDataHolder(String location, String phone, String shopName,String email) {
        Location = location;
        Phone = phone;
        this.shopName = shopName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
