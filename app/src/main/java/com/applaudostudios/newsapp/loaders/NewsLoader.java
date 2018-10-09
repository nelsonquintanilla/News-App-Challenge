package com.applaudostudios.newsapp.loaders;

import android.content.ContentValues;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.applaudostudios.newsapp.utils.QueryUtils;
import com.applaudostudios.newsapp.model.News;

import java.util.List;
import com.applaudostudios.newsapp.data.NewsContract.NewsEntry;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    /**
     * Query URL
     */
    private String mUrl;
    private String mCategory;

    public NewsLoader(Context context, String url, String category) {
        super(context);
        mUrl = url;
        mCategory = category;
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
        List<News> newsList = QueryUtils.fetchEarthquakeData(mUrl);

        getContext().getContentResolver().delete(
                NewsEntry.CONTENT_URI,
                NewsEntry.COLUMN_NEWS_CATEGORY + "=?",
                new String[] {mCategory});

        // Inserting list of news in the database.
        for (News news : newsList) {
            // Create a ContentValues object where column names are the keys,
            // and news attributes extracted from the JSON are the values.
            ContentValues values = new ContentValues();
            values.put(NewsEntry.COLUMN_NEWS_HEADLINE, news.getHeadline());
            values.put(NewsEntry.COLUMN_NEWS_CATEGORY, mCategory);
            values.put(NewsEntry.COLUMN_NEWS_BODY_TEXT, news.getBodyText());
            values.put(NewsEntry.COLUMN_NEWS_THUMBNAIL, news.getThumbnail());
            values.put(NewsEntry.COLUMN_NEWS_WEB_URL, news.getWebUrl());

            // returning the content URI for the new pet.
            getContext().getContentResolver().insert(NewsEntry.CONTENT_URI, values);
        }

        return newsList;
    }
}
