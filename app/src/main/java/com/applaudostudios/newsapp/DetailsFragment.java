package com.applaudostudios.newsapp;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.applaudostudios.newsapp.model.News;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Bitmap> {
    private News mNews;
    private String mHeadline;
    private String mBodyText;
    private ImageView thumbnail;
    private View mView;
    public static final int THUMBNAIL_LOADER_ID = 2;


    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(News news, String url) {
        Bundle args = new Bundle();
        args.putParcelable("NEWS_KEY", news);
        args.putString("someUrl", url);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNews = getArguments().getParcelable("NEWS_KEY");
        mHeadline = mNews.getHeadline();
        mBodyText = mNews.getBodyText();
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.restartLoader(THUMBNAIL_LOADER_ID, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       mView = inflater.inflate(R.layout.fragment_details, container, false);
       TextView headline = mView.findViewById(R.id.headline_text_view);
       headline.setText(mHeadline);
       TextView bodyText = mView.findViewById(R.id.bodytext_text_view);
       bodyText.setText(mBodyText);
       thumbnail = mView.findViewById(R.id.thumbnail_image);
       thumbnail.setVisibility(View.INVISIBLE);
       return mView;
    }


    @NonNull
    @Override
    public Loader<Bitmap> onCreateLoader(int id, @Nullable Bundle args) {
        return new ThumbnailLoader(getActivity(), getArguments().getString("someUrl"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Bitmap> loader, Bitmap data) {
        thumbnail.setImageBitmap(data);
        thumbnail.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Bitmap> loader) {
        // Empty.
    }

}
