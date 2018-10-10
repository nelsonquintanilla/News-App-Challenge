package com.applaudostudios.newsapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.applaudostudios.newsapp.R;
import com.applaudostudios.newsapp.fragment.DetailsFragment;
import com.applaudostudios.newsapp.model.News;

public class DetailsActivity extends AppCompatActivity {
    private static final String EXTRA_DETAIL = "EXTRA_DETAIL";

    // Makes the intent to start the details activity and pass in the details of the news clicked.
    public static Intent putNews(Context context, News news) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_DETAIL, news);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Getting the details of the news clicked.
        News mNews = getIntent().getParcelableExtra(EXTRA_DETAIL);

        // Creates a new instance of the details fragment and pass in the news details to show them
        // in there.
        DetailsFragment fragment = DetailsFragment.newInstance(mNews, mNews.getThumbnail(), mNews.getNewsId());

        // To communicate between this activity and the fragment created above.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        // Sets the up button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    // Up button logic
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
