package com.applaudostudios.newsapp.fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.applaudostudios.newsapp.R;
import com.applaudostudios.newsapp.loader.ThumbnailLoader;
import com.applaudostudios.newsapp.model.News;
import com.applaudostudios.newsapp.data.NewsContract.SavedNewsEntry;

import java.util.Objects;

/**
 * A simple Fragment subclass.
 */
public class DetailsFragment extends Fragment implements LoaderManager.LoaderCallbacks,
        View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private String mHeadline;
    private String mBodyText;
    private String mWebUrl;
    private ImageView mThumbnail;
    public static final int CURSOR_LOADER_ID = 1;
    public static final int THUMBNAIL_LOADER_ID = 2;
    private News mNewsDetails;
    private Cursor mCursor;
    private boolean mVariable;
    private ToggleButton readMeLaterButton;


    public DetailsFragment() {
        // Required empty public constructor
    }

    // Creates a new instance of the fragment and takes as arguments an url (the thumbnail url)
    // and the details of the news clicked.
    public static DetailsFragment newInstance(News news, String url, String id) {
        Bundle args = new Bundle();
        args.putParcelable("NEWS_KEY", news);
        args.putString("someUrl", url);
        args.putString("someId", id);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        News mNews = (getArguments()).getParcelable("NEWS_KEY");
        mNewsDetails = mNews;
        mHeadline = mNews.getHeadline();
        mBodyText = mNews.getBodyText();
        mWebUrl = mNews.getWebUrl();
        // Declares loadManager
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.restartLoader(THUMBNAIL_LOADER_ID, null, this);
        loaderManager.initLoader(CURSOR_LOADER_ID, null, this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflates the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_details, container, false);

        TextView headline = mView.findViewById(R.id.headline_text_view);
        headline.setText(mHeadline);
        TextView bodyText = mView.findViewById(R.id.bodytext_text_view);
        bodyText.setText(mBodyText);
        mThumbnail = mView.findViewById(R.id.thumbnail_image);
        mThumbnail.setVisibility(View.INVISIBLE);
        ImageView webUrlImage = mView.findViewById(R.id.web_url_image);
        webUrlImage.setOnClickListener(this);
        readMeLaterButton = mView.findViewById(R.id.read_me_later_button);
        readMeLaterButton.setOnCheckedChangeListener(this);
        return mView;
    }

    // Methods required because of the implementation of the LoaderCallbacks interface
    @NonNull
    @Override
    public Loader onCreateLoader(int id, @Nullable Bundle args) {
        switch (id){
            case THUMBNAIL_LOADER_ID:
                return new ThumbnailLoader(Objects.requireNonNull(getActivity()),
                        Objects.requireNonNull(getArguments()).getString("someUrl"));
            case CURSOR_LOADER_ID:
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
                            SavedNewsEntry.COLUMN_NEWS_ID + "=?",
                            // No selection arguments
                            new String[] {(getArguments()).getString("someId")},
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
        switch (loader.getId()) {
            case THUMBNAIL_LOADER_ID:
                mThumbnail.setImageBitmap((Bitmap) data);
                mThumbnail.setVisibility(View.VISIBLE);
                break;

            case CURSOR_LOADER_ID:
                mVariable = getCursorData(data);
                if(mVariable){
                    readMeLaterButton.setChecked(true);
                } else {
                    readMeLaterButton.setChecked(false);
                }
                break;

            default:
                break;
        }
    }

    public boolean getCursorData(Object data){
        mCursor = (Cursor) data;
        // If table has rows.
        if(mCursor.moveToFirst()){
            return true;
        }
        return false;
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {
        switch (loader.getId()){
            case CURSOR_LOADER_ID:
                mCursor.close();
                break;
            default:
                break;
        }
    }


    // Makes the intent to open the url in a browser when the webUrl image is clicked.
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.web_url_image:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(mWebUrl));
                startActivity(i);
                break;
            default:
                break;
        }
    }

    public void insertNews() {
        ContentValues values = new ContentValues();
        values.put(SavedNewsEntry.COLUMN_NEWS_ID, mNewsDetails.getNewsId());
        values.put(SavedNewsEntry.COLUMN_NEWS_HEADLINE, mNewsDetails.getHeadline());
        values.put(SavedNewsEntry.COLUMN_NEWS_BODY_TEXT, mNewsDetails.getBodyText());
        values.put(SavedNewsEntry.COLUMN_NEWS_THUMBNAIL, mNewsDetails.getThumbnail());
        values.put(SavedNewsEntry.COLUMN_NEWS_WEB_URL, mNewsDetails.getWebUrl());

        // returning the content URI.
        getContext().getContentResolver().insert(SavedNewsEntry.CONTENT_URI, values);
    }

    public void deleteNews() {
        // returning the content URI.
        getContext().getContentResolver().delete(
                SavedNewsEntry.CONTENT_URI,
                SavedNewsEntry.COLUMN_NEWS_ID + "=?",
                new String[] {(getArguments()).getString("someId")});
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if(!mVariable)
            insertNews();
        } else {
            deleteNews();
        }
    }

}
