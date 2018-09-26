package com.applaudostudios.newsapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applaudostudios.newsapp.adapters.RecyclerViewAdapter;
import com.applaudostudios.newsapp.model.News;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements CallBack {
    private View mView;
    private List<News> mNewsList;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_category, container, false);
        RecyclerView myRecyclerView = mView.findViewById(R.id.recycler_view_news);
        myRecyclerView.setHasFixedSize(true);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(mNewsList, this);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerViewAdapter);
        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsList = new ArrayList<>();
        mNewsList.add(new News("Headline blablabla"));
        mNewsList.add(new News("Headline blablabla"));
        mNewsList.add(new News("Headline blablabla"));
        mNewsList.add(new News("Headline blablabla"));
        mNewsList.add(new News("Headline blablabla"));
        mNewsList.add(new News("Headline blablabla"));
        mNewsList.add(new News("Headline blablabla"));
        mNewsList.add(new News("Headline blablabla"));
        mNewsList.add(new News("Headline blablabla"));
        mNewsList.add(new News("Headline blablabla"));

    }

    @Override
    public void onItemClick(int position) {
//        startActivity(DetailsActivity.getInstance(getContext(), mNewsList.get(position)));
    }


}
