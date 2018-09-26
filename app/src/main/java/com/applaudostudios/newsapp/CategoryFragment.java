package com.applaudostudios.newsapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applaudostudios.newsapp.adapters.RecyclerViewAdapter;
import com.applaudostudios.newsapp.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        mNewsList = QueryUtils.extractNews();
//        mNewsList = new ArrayList<>();
//        mNewsList.add(new News("Headline blablabla"));
//        mNewsList.add(new News("Headline blablabla"));
//        mNewsList.add(new News("Headline blablabla"));
//        mNewsList.add(new News("Headline blablabla"));
//        mNewsList.add(new News("Headline blablabla"));
//        mNewsList.add(new News("Headline blablabla"));
//        mNewsList.add(new News("Headline blablabla"));
//        mNewsList.add(new News("Headline blablabla"));
//        mNewsList.add(new News("Headline blablabla"));
//        mNewsList.add(new News("Headline blablabla"));

    }

    public static class QueryUtils {
        private static final String SAMPLE_JSON_RESPONSE = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":201969,\"startIndex\":1,\"pageSize\":5,\"currentPage\":1,\"pages\":40394,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"football/2018/sep/19/psg-liverpool-thomas-tuchel-football\",\"type\":\"article\",\"sectionId\":\"football\",\"sectionName\":\"Football\",\"webPublicationDate\":\"2018-09-19T10:07:24Z\",\"webTitle\":\"PSG are not playing Tuchel football. It may not even be football | Jonathan Wilson\",\"webUrl\":\"https://www.theguardian.com/football/2018/sep/19/psg-liverpool-thomas-tuchel-football\",\"apiUrl\":\"https://content.guardianapis.com/football/2018/sep/19/psg-liverpool-thomas-tuchel-football\",\"fields\":{\"headline\":\"PSG are not playing Tuchel football. It may not even be football\",\"thumbnail\":\"https://media.guim.co.uk/57fbfeaea1ce71ec5769c04dae693cb047427d3c/0_136_3212_1928/500.jpg\"},\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"society/2018/sep/13/the-normalisation-of-gambling-in-football\",\"type\":\"article\",\"sectionId\":\"society\",\"sectionName\":\"Society\",\"webPublicationDate\":\"2018-09-13T17:18:23Z\",\"webTitle\":\"The normalisation of gambling in football | Letter\",\"webUrl\":\"https://www.theguardian.com/society/2018/sep/13/the-normalisation-of-gambling-in-football\",\"apiUrl\":\"https://content.guardianapis.com/society/2018/sep/13/the-normalisation-of-gambling-in-football\",\"fields\":{\"headline\":\"The normalisation of gambling in football\",\"thumbnail\":\"https://media.guim.co.uk/8965824fc91d636fd74d50ccc6ba72801caa2481/39_140_3138_1883/500.jpg\"},\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"football/2018/sep/24/football-rumours-zidane-manage-beckhams-mls-franchise\",\"type\":\"article\",\"sectionId\":\"football\",\"sectionName\":\"Football\",\"webPublicationDate\":\"2018-09-24T08:04:47Z\",\"webTitle\":\"Football rumours: Zidane to manage Beckham's MLS franchise?\",\"webUrl\":\"https://www.theguardian.com/football/2018/sep/24/football-rumours-zidane-manage-beckhams-mls-franchise\",\"apiUrl\":\"https://content.guardianapis.com/football/2018/sep/24/football-rumours-zidane-manage-beckhams-mls-franchise\",\"fields\":{\"headline\":\"Football rumours: Zidane to manage Beckham's MLS franchise?\",\"thumbnail\":\"https://media.guim.co.uk/af95cedd389468096ce4ed9b8350eeff8db97464/0_134_2629_1577/500.jpg\"},\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"football/2018/sep/22/football-league-birmingham-inflict-first-league-defeat-on-leeds-united\",\"type\":\"article\",\"sectionId\":\"football\",\"sectionName\":\"Football\",\"webPublicationDate\":\"2018-09-22T17:35:42Z\",\"webTitle\":\"Football League: Birmingham inflict first league defeat on Leeds United\",\"webUrl\":\"https://www.theguardian.com/football/2018/sep/22/football-league-birmingham-inflict-first-league-defeat-on-leeds-united\",\"apiUrl\":\"https://content.guardianapis.com/football/2018/sep/22/football-league-birmingham-inflict-first-league-defeat-on-leeds-united\",\"fields\":{\"headline\":\"Football League: Birmingham inflict first league defeat on Leeds United\",\"thumbnail\":\"https://media.guim.co.uk/fc886e3ead7f1d57e29f36f786e48c65bdfd8aab/0_40_3098_1859/500.jpg\"},\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"football/2018/sep/25/football-transfer-rumour-mill-mario-gotze-to-liverpool-borussia-dortmund\",\"type\":\"article\",\"sectionId\":\"football\",\"sectionName\":\"Football\",\"webPublicationDate\":\"2018-09-25T07:58:54Z\",\"webTitle\":\"Football transfer rumours: Borussia Dortmund's Mario Götze to Liverpool?\",\"webUrl\":\"https://www.theguardian.com/football/2018/sep/25/football-transfer-rumour-mill-mario-gotze-to-liverpool-borussia-dortmund\",\"apiUrl\":\"https://content.guardianapis.com/football/2018/sep/25/football-transfer-rumour-mill-mario-gotze-to-liverpool-borussia-dortmund\",\"fields\":{\"headline\":\"Football transfer rumours: Borussia Dortmund's Mario Götze to Liverpool?\",\"thumbnail\":\"https://media.guim.co.uk/c5d6972d1705c79e552cde99c5b8f7649b2841a9/0_181_3500_2100/500.jpg\"},\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"}]}}";

        public QueryUtils() {
        }

        static List<News> extractNews() {
            List<News> mNewsList;
            mNewsList = new ArrayList<>();

            try {
                JSONObject root = new JSONObject(SAMPLE_JSON_RESPONSE);
                JSONObject response = root.getJSONObject("response");
                JSONArray results = response.getJSONArray("results");

                for (int i = 0; i < results.length(); i++) {
                    JSONObject currentNews = results.getJSONObject(i);
                    JSONObject fields = currentNews.getJSONObject("fields");

                    String headline = fields.getString("headline");

                    News news = new News(headline);
                    mNewsList.add(news);
                }

            } catch (JSONException e) {
                Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
            }

            return mNewsList;
        }
    }

    @Override
    public void onItemClick(int position) {
//        startActivity(DetailsActivity.getInstance(getContext(), mNewsList.get(position)));
    }


}
