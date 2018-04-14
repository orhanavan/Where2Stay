package com.w2s.orhan.where2stay.Advert;

public class Upload {
    private String title;
    private String cost;
    private String imageURL;

    public Upload() {
    }
    public Upload(String title, String cost, String imageURL) {
        this.title = title;
        this.cost = cost;
        this.imageURL = imageURL;
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
}
