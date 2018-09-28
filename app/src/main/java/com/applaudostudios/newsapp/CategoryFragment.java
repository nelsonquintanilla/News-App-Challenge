package com.applaudostudios.newsapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applaudostudios.newsapp.adapters.RecyclerViewAdapter;
import com.applaudostudios.newsapp.model.News;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements CallBack, LoaderManager.LoaderCallbacks<List<News>> {

    private TextView mEmptyStateTextView;

    public static final int NEWS_LOADER_ID = 1;
    private int page;
    private View mView;
    private RecyclerViewAdapter recyclerViewAdapter;


    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance(int page, String url){
        CategoryFragment categoryFragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someUrl", url);
        categoryFragment.setArguments(args);
        return categoryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.restartLoader(NEWS_LOADER_ID, null, this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_category, container, false);
        RecyclerView myRecyclerView = mView.findViewById(R.id.recycler_view_news);
        myRecyclerView.setHasFixedSize(true);
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerViewAdapter);
        return mView;
    }

    @Override
    public void onItemClick(int position) {
//        startActivity(DetailsActivity.getInstance(getContext(), mNewsList.get(position)));
    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {
        return new NewsLoader(getActivity(), getArguments().getString("someUrl"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> data) {
//        View loadingIndicator = getView().findViewById(R.id.loading_indicator);
//        loadingIndicator.setVisibility(View.GONE);
        recyclerViewAdapter.setData(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
    }

}
