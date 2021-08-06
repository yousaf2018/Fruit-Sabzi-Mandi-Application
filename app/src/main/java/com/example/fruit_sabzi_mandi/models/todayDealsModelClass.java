package com.example.fruit_sabzi_mandi.models;

import android.widget.ImageView;

public class todayDealsModelClass {
    private String todayDealTitle;
    private int todayDealImage;
    private String todayDealDate;
    private String todayDealLocation;
    private String todayDealPrice;
    private String todayDealContact;

    public todayDealsModelClass(String todayDealTitle, int  todayDealImage, String todayDealDate, String todayDealLocation, String todayDealPrice,String todayDealContact) {
        this.todayDealTitle = todayDealTitle;
        this.todayDealImage = todayDealImage;
        this.todayDealDate = todayDealDate;
        this.todayDealLocation = todayDealLocation;
        this.todayDealPrice = todayDealPrice;
        this.todayDealContact = todayDealContact;
    }

    public String getTodayDealContact() {
        return todayDealContact;
    }

    public void setTodayDealContact(String todayDealContact) {
        this.todayDealContact = todayDealContact;
    }

    public String getTodayDealTitle() {
        return todayDealTitle;
    }

    public void setTodayDealTitle(String todayDealTitle) {
        this.todayDealTitle = todayDealTitle;
    }

    public int getTodayDealImage() {
        return todayDealImage;
    }

    public void setTodayDealImage(int todayDealImage) {
        this.todayDealImage = todayDealImage;
    }

    public String getTodayDealDate() {
        return todayDealDate;
    }

    public void setTodayDealDate(String todayDealDate) {
        this.todayDealDate = todayDealDate;
    }

    public String getTodayDealLocation() {
        return todayDealLocation;
    }

    public void setTodayDealLocation(String todayDealLocation) {
        this.todayDealLocation = todayDealLocation;
    }

    public String getTodayDealPrice() {
        return todayDealPrice;
    }

    public void setTodayDealPrice(String todayDealPrice) {
        this.todayDealPrice = todayDealPrice;
    }
}
