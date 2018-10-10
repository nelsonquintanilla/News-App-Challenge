package com.applaudostudios.newsapp.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applaudostudios.newsapp.R;
import com.applaudostudios.newsapp.activities.DetailsActivity;
import com.applaudostudios.newsapp.adapters.RecyclerViewAdapter;
import com.applaudostudios.newsapp.model.News;
import com.applaudostudios.newsapp.data.NewsContract.SavedNewsEntry;


import java.util.ArrayList;
import java.util.List;

public class ReadMeLaterFragment extends Fragment implements RecyclerViewAdapter.CallBack, LoaderManager.LoaderCallbacks {
    private List<News> mData;
    public static final int CURSOR_LOADER_ID = 1;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private Cursor mCursor;

    public ReadMeLaterFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Declares loadManager
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(CURSOR_LOADER_ID, null, this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_read_me, container, false);

        // Declares and initializes the recyclerView object
        RecyclerView myRecyclerView = mView.findViewById(R.id.recycler_view_read_me);
        myRecyclerView.setHasFixedSize(true);
        // Sets up the adapter
        mRecyclerViewAdapter = new RecyclerViewAdapter(this);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(mRecyclerViewAdapter);
        return mView;
    }

    @NonNull
    @Override
    public Loader onCreateLoader(int id, @Nullable Bundle args) {
        // Define a projection that specifies the columns from the table we care about.
        String[] projection = {
                SavedNewsEntry._ID,
                SavedNewsEntry.COLUMN_NEWS_ID,
                SavedNewsEntry.COLUMN_NEWS_HEADLINE,
                SavedNewsEntry.COLUMN_NEWS_BODY_TEXT,
                SavedNewsEntry.COLUMN_NEWS_THUMBNAIL,
                SavedNewsEntry.COLUMN_NEWS_WEB_URL};

        // This loader will execute the ContentProvider's query method on a background thread
        // Parent activity context
        if(getContext()!=null){
            return new CursorLoader( getContext(),
                    // Provider content URI to query
                    SavedNewsEntry.CONTENT_URI,
                    // Columns to include in the resulting Cursor
                    projection,
                    // No selection clause
                    null,
                    // No selection arguments
                    null,
                    // Default sort order
                    null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader loader, Object data) {
        List<News> dataExtracted = getCursorData(data);
        // Updates the data in the adapter
        mRecyclerViewAdapter.setData(dataExtracted);
        // Saving data to use it later on on the onItemClickMethod
        mData = dataExtracted;
    }

    public List<News> getCursorData(Object data){
        mCursor = (Cursor) data;
        List<News> newsList = new ArrayList<>();
        newsList.clear();

        // If table has rows.
        if(mCursor.moveToFirst()){
            // Loop through the table rows.
            do{
                News news = new News("", "", "", "","", "");
                news.setNewsId( mCursor.getString(mCursor.getColumnIndex(SavedNewsEntry.COLUMN_NEWS_ID)) );
                news.setHeadline( mCursor.getString(mCursor.getColumnIndex(SavedNewsEntry.COLUMN_NEWS_HEADLINE)) );
                news.setBodyText( mCursor.getString(mCursor.getColumnIndex(SavedNewsEntry.COLUMN_NEWS_BODY_TEXT)) );
                news.setThumbnail( mCursor.getString(mCursor.getColumnIndex(SavedNewsEntry.COLUMN_NEWS_THUMBNAIL)) );
                news.setWebUrl( mCursor.getString(mCursor.getColumnIndex(SavedNewsEntry.COLUMN_NEWS_WEB_URL)) );
                newsList.add(news);
            } while (mCursor.moveToNext());
        }
        return newsList;
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {
        mCursor.close();
    }

    @Override
    public void onItemClick(int position) {
        startActivity(DetailsActivity.putNews(getContext(), mData.get(position)));
    }

}
