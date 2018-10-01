package com.applaudostudios.newsapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applaudostudios.newsapp.model.News;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {
    private News mNews;
    private String mHeadline;
    private String mBodyText;
    private String mThumbnail;
    private View mView;

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(News news) {
        Bundle args = new Bundle();
        args.putParcelable("NEWS_KEY", news);
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
        mThumbnail = mNews.getThumbnail();
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
       return mView;
    }

}
