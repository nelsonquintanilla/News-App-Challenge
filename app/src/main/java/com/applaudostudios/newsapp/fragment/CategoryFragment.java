package com.applaudostudios.newsapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import com.applaudostudios.newsapp.activity.DetailsActivity;
import com.applaudostudios.newsapp.activity.ReadMeActivity;
import com.applaudostudios.newsapp.adapter.RecyclerViewAdapter;
import com.applaudostudios.newsapp.data.NewsContract.NewsEntry;
import com.applaudostudios.newsapp.loader.NewsLoader;
import com.applaudostudios.newsapp.model.News;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements RecyclerViewAdapter.CallBack, LoaderManager.LoaderCallbacks {

    private List<News> mData;
    public static final int NEWS_LOADER_ID = 0;
    public static final int CURSOR_LOADER_ID = 1;
    private RecyclerViewAdapter recyclerViewAdapter;
    private Cursor mCursor;

    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Creates a fragment depending on the category that it takes as an argument.
     *
     * @param url in this case, it's an url build by the buildUrl method
     * @return an instance of CategoryFragment
     */
    public static CategoryFragment newInstance(String url, String category) {
        CategoryFragment categoryFragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString("someUrl", url);
        args.putString("someCategory", category);
        categoryFragment.setArguments(args);
        return categoryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_category, container, false);
        // Setup FAB to open EditorActivity
        FloatingActionButton fab = mView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ReadMeActivity.class);
                startActivity(intent);
            }
        });

        // Checks the status of the network connection
        ConnectivityManager connMgr = (ConnectivityManager)
                (getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        // Gets a reference to the LoaderManager, in order to interact with loaders
        LoaderManager loaderManager;

        // If there is a network connection, fetches data
        if (networkInfo != null && networkInfo.isConnected()) {
            loaderManager = getLoaderManager();
            // Initializes the loader
            loaderManager.restartLoader(NEWS_LOADER_ID, null, this);

        } else {

            View loadingIndicator = mView.findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            loaderManager = getLoaderManager();
            loaderManager.initLoader(CURSOR_LOADER_ID, null, this);

        }

        // Declares and initializes the recyclerView object
        RecyclerView myRecyclerView = mView.findViewById(R.id.recycler_view_news);
        myRecyclerView.setHasFixedSize(true);
        // Sets up the adapter
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerViewAdapter);
        return mView;
    }


    // Methods required because of the implementation of the LoaderCallbacks interface
    @NonNull
    @Override
    public Loader onCreateLoader(int id, @Nullable Bundle args) {
        switch (id) {
            case NEWS_LOADER_ID:
                return new NewsLoader(getActivity(), (getArguments()).getString("someUrl"),
                        (getArguments()).getString("someCategory"));

            case CURSOR_LOADER_ID:
                // Define a projection that specifies the columns from the table we care about.
                String[] projection = {
                        NewsEntry._ID,
                        NewsEntry.COLUMN_NEWS_ID,
                        NewsEntry.COLUMN_NEWS_CATEGORY,
                        NewsEntry.COLUMN_NEWS_HEADLINE,
                        NewsEntry.COLUMN_NEWS_BODY_TEXT,
                        NewsEntry.COLUMN_NEWS_THUMBNAIL,
                        NewsEntry.COLUMN_NEWS_WEB_URL};

                // This loader will execute the ContentProvider's query method on a background thread
                // Parent activity context
                if (getContext() != null) {
                    return new CursorLoader(getContext(),
                            // Provider content URI to query
                            NewsEntry.CONTENT_URI,
                            // Columns to include in the resulting Cursor
                            projection,
                            // No selection clause
                            NewsEntry.COLUMN_NEWS_CATEGORY + "=?",
                            // No selection arguments
                            new String[]{(getArguments()).getString("someCategory")},
                            // Default sort order
                            null);
                }
            default:
                break;
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader loader, Object data) {
        View loadingIndicator;
        switch (loader.getId()) {
            case NEWS_LOADER_ID:
                // Removing loading indicator
                loadingIndicator = (getView()).findViewById(R.id.loading_indicator);
                loadingIndicator.setVisibility(View.GONE);
                // Updates the data in the adapter
                recyclerViewAdapter.setData((List<News>) data);
                // Saving data to use it later on on the onItemClickMethod
                mData = (List<News>) data;
                break;

            case CURSOR_LOADER_ID:
                // Removing loading indicator
                loadingIndicator = (getView()).findViewById(R.id.loading_indicator);
                loadingIndicator.setVisibility(View.GONE);
                List<News> dataExtracted = getCursorData(data);
                // Updates the data in the adapter
                recyclerViewAdapter.setData(dataExtracted);
                // Saving data to use it later on on the onItemClickMethod
                mData = dataExtracted;
                break;

            default:
                break;
        }
    }

    public List<News> getCursorData(Object data) {
        mCursor = (Cursor) data;
        List<News> newsList = new ArrayList<>();
        newsList.clear();

        // If table has rows.
        if (mCursor.moveToFirst()) {
            // Loop through the table rows.
            do {
                News news = new News("", "", "", "", "", "");
                news.setNewsId(mCursor.getString(mCursor.getColumnIndex(NewsEntry.COLUMN_NEWS_ID)));
                news.setHeadline(mCursor.getString(mCursor.getColumnIndex(NewsEntry.COLUMN_NEWS_HEADLINE)));
                news.setBodyText(mCursor.getString(mCursor.getColumnIndex(NewsEntry.COLUMN_NEWS_BODY_TEXT)));
                news.setThumbnail(mCursor.getString(mCursor.getColumnIndex(NewsEntry.COLUMN_NEWS_THUMBNAIL)));
                news.setWebUrl(mCursor.getString(mCursor.getColumnIndex(NewsEntry.COLUMN_NEWS_WEB_URL)));
                newsList.add(news);
            } while (mCursor.moveToNext());
        }
        return newsList;
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {
        switch (loader.getId()) {
            case CURSOR_LOADER_ID:
                mCursor.close();
                break;
            default:
                break;
        }
    }

    // Method required because of the CallBack interface implementation.
    @Override
    public void onItemClick(int position) {
        startActivity(DetailsActivity.putNews(getContext(), mData.get(position)));
    }

}
