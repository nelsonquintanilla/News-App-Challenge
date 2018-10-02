package com.applaudostudios.newsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Bitmap>, View.OnClickListener {
    private String mHeadline;
    private String mBodyText;
    private String mWebUrl;
    private ImageView mThumbnail;
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
        News mNews = Objects.requireNonNull(getArguments()).getParcelable("NEWS_KEY");
        mHeadline = Objects.requireNonNull(mNews).getHeadline();
        mBodyText = mNews.getBodyText();
        mWebUrl = mNews.getWebUrl();
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.restartLoader(THUMBNAIL_LOADER_ID, null, this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_details, container, false);
        TextView headline = mView.findViewById(R.id.headline_text_view);
        headline.setText(mHeadline);
        TextView bodyText = mView.findViewById(R.id.bodytext_text_view);
        bodyText.setText(mBodyText);
        mThumbnail = mView.findViewById(R.id.thumbnail_image);
        mThumbnail.setVisibility(View.INVISIBLE);
        ImageView webUrlImage = mView.findViewById(R.id.web_url_image);
        webUrlImage.setOnClickListener(this);
        return mView;
    }


    @NonNull
    @Override
    public Loader<Bitmap> onCreateLoader(int id, @Nullable Bundle args) {
        return new ThumbnailLoader(Objects.requireNonNull(getActivity()),
                Objects.requireNonNull(getArguments()).getString("someUrl"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Bitmap> loader, Bitmap data) {
        mThumbnail.setImageBitmap(data);
        mThumbnail.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Bitmap> loader) {
        // Empty.
    }

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

}
