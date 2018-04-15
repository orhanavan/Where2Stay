package com.w2s.orhan.where2stay.Advert;

import android.support.v7.widget.RecyclerView;

public class Upload {
    private String title;
    private String cost;
    private String imageURL;
    private String advertID;


    public Upload() {
    }
    public Upload(String title, String cost, String imageURL, String advertID) {
        this.title = title;
        this.cost = cost;
        this.imageURL = imageURL;
        this.advertID = advertID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getAdvertID() {
        return advertID;
    }

    public void setAdvertID(String advertID) {
        this.advertID = advertID;
    }
}
