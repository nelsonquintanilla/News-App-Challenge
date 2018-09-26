package com.applaudostudios.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

public class NewsLoader extends AsyncTaskLoader<String> {

    private String mQueryString;

    public NewsLoader(Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        forceLoad();
    }

    @Override
    public String loadInBackground() {
        return NetworkUtils.getNewsInfo(mQueryString);
    }
}
