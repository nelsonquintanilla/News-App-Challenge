package com.applaudostudios.newsapp.model;

public class News {
    private String mTitle;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public News(String headline) {
        this.mTitle = headline;
    }
}
