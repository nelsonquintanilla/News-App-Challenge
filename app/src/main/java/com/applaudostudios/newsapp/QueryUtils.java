package com.applaudostudios.newsapp;

import android.text.TextUtils;
import android.util.Log;

import com.applaudostudios.newsapp.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    public QueryUtils() {
    }

    public static List<News> fetchEarthquakeData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        return extractResultFromJson(jsonResponse);
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            // Setting the http request.
            //
            // Opens the URL connection and makes the request.
            urlConnection = (HttpURLConnection) url.openConnection();
            // Sets the read timeout to a specified timeout, in milliseconds.
            urlConnection.setReadTimeout(10000);
            // Sets a specified timeout value, in milliseconds.
            urlConnection.setConnectTimeout(15000);
            // Set the method for the URL request.
            urlConnection.setRequestMethod("GET");
            // Establishes http connection with the server.
            urlConnection.connect();

            // Receiving the response and making sense of it for our app.
            //
            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the news JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<News> extractResultFromJson(String newsJson) {

        if (TextUtils.isEmpty(newsJson)) {
            return null;
        }
        List<News> newsList = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(newsJson);
            JSONObject response = root.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                News news = new News("", "", "", "");

                JSONObject currentNews = results.getJSONObject(i);
                JSONObject fields = currentNews.getJSONObject("fields");

                if (currentNews.has("webUrl"))
                    news.setWebUrl(currentNews.getString("webUrl"));

                if (fields.has("headline"))
                    news.setHeadline(fields.getString("headline"));

                if (fields.has("bodyText"))
                    news.setBodyText(fields.getString("bodyText"));

                if (fields.has("thumbnail"))
                    news.setThumbnail(fields.getString("thumbnail"));

                newsList.add(news);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }

        return newsList;
    }
}
