package com.applaudostudios.newsapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import java.io.InputStream;

public class ThumbnailLoader extends AsyncTaskLoader<Bitmap> {

    private String mUrl;

    public ThumbnailLoader(@NonNull Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public Bitmap loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        Bitmap bmThumbnail = null;
        try {
            InputStream in = new java.net.URL(mUrl).openStream();
            bmThumbnail = BitmapFactory.decodeStream(in);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();

        }
        return bmThumbnail;
    }

}
