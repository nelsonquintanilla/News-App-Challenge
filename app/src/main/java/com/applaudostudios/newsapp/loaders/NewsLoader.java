package com.applaudostudios.newsapp.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.applaudostudios.newsapp.utils.QueryUtils;
import com.applaudostudios.newsapp.model.News;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    /**
     * Query URL
     */
    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        // Starts the load of the AsyncTask
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of news.
        return QueryUtils.fetchEarthquakeData(mUrl);
    }
}
