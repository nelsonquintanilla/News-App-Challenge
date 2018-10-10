package com.applaudostudios.newsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable {

    private String mHeadline;
    private String mBodyText;
    private String mThumbnail;
    private String mWebUrl;
    private String mCategory;
    private String mNewsId;

    public String getNewsId() {
        return mNewsId;
    }

    public void setNewsId(String mNewsId) {
        this.mNewsId = mNewsId;
    }

    public News(String mHeadline, String mBodyText, String mThumbnail, String mWebUrl, String mCategory, String mNewsId) {
        this.mHeadline = mHeadline;
        this.mBodyText = mBodyText;
        this.mThumbnail = mThumbnail;
        this.mWebUrl = mWebUrl;
        this.mCategory = mCategory;
        this.mNewsId = mNewsId;
    }

    public String getHeadline() {
        return mHeadline;
    }

    public void setHeadline(String mTitle) {
        this.mHeadline = mTitle;
    }

    public String getBodyText() {
        return mBodyText;
    }

    public void setBodyText(String mBodyText) {
        this.mBodyText = mBodyText;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(String mThumbnail) {
        this.mThumbnail = mThumbnail;
    }

    public String getWebUrl() {
        return mWebUrl;
    }

    public void setWebUrl(String mWebUrl) {
        this.mWebUrl = mWebUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mHeadline);
        dest.writeString(this.mBodyText);
        dest.writeString(this.mThumbnail);
        dest.writeString(this.mWebUrl);
        dest.writeString(this.mCategory);
        dest.writeString(this.mNewsId);
    }

    protected News(Parcel in) {
        this.mHeadline = in.readString();
        this.mBodyText = in.readString();
        this.mThumbnail = in.readString();
        this.mWebUrl = in.readString();
        this.mCategory = in.readString();
        this.mNewsId = in.readString();
    }

    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}
