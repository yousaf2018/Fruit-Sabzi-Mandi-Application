package com.example.fruit_sabzi_mandi.models;

import android.view.Display;

public class todayDealsModelClass {
    private String imageUrl;
    private String todayDealTitle;
    private String todayDealPrice;
    private String todayDealLocation;
    private String todayDealDate;
    private String todayDealContact;
    public todayDealsModelClass(){

    }
    public todayDealsModelClass(String imageUrl, String todayDealTitle, String todayDealPrice, String todayDealLocation, String todayDealDate, String todayDealContact) {
        this.imageUrl = imageUrl;
        this.todayDealTitle = todayDealTitle;
        this.todayDealPrice = todayDealPrice;
        this.todayDealLocation = todayDealLocation;
        this.todayDealDate = todayDealDate;
        this.todayDealContact = todayDealContact;
    }

    public String getTodayDealTitle() {
        return todayDealTitle;
    }

    public void setTodayDealTitle(String todayDealTitle) {
        this.todayDealTitle = todayDealTitle;
    }

    public String getTodayDealPrice() {
        return todayDealPrice;
    }

    public void setTodayDealPrice(String todayDealPrice) {
        this.todayDealPrice = todayDealPrice;
    }

    public String getTodayDealLocation() {
        return todayDealLocation;
    }

    public void setTodayDealLocation(String todayDealLocation) {
        this.todayDealLocation = todayDealLocation;
    }

    public String getTodayDealDate() {
        return todayDealDate;
    }

    public void setTodayDealDate(String todayDealDate) {
        this.todayDealDate = todayDealDate;
    }

    public String getTodayDealContact() {
        return todayDealContact;
    }

    public void setTodayDealContact(String todayDealContact) {
        this.todayDealContact = todayDealContact;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
