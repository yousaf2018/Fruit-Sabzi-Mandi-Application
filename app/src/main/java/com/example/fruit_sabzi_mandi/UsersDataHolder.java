package com.example.fruit_sabzi_mandi;

public class UsersDataHolder {
    private String Location;
    private String Phone;
    private String shopName;

    public UsersDataHolder(){
    }

    public UsersDataHolder(String location, String phone, String shopName) {
        Location = location;
        Phone = phone;
        this.shopName = shopName;
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
