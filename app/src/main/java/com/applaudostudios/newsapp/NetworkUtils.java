//package com.applaudostudios.newsapp;
//
//import android.net.Uri;
//import android.util.Log;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class NetworkUtils {
//
//    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
//
//    // Constants for the various components of the Content API request.
//    //
//    // Base endpoint URL for the Content API.
//    private static final String BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
//
//    private static final String PAGE_PARAM = "page";
//    // Parameter that limits search results.
//    private static final String PAGE_SIZE_PARAM = "page-size";
//    // Parameter for the search string.
//    private static final String QUERY_PARAM = "q";
//
//    private static final String API_KEY = "api-key";
//
//    static String getNewsInfo(String queryString) {
//        HttpURLConnection urlConnection = null;
//        BufferedReader reader = null;
//        String bookJSONString = null;
//
//        try {
//            Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
//                    .appendQueryParameter(PAGE_PARAM, "1")
//                    .appendQueryParameter(PAGE_SIZE_PARAM, "5")
//                    .appendQueryParameter(QUERY_PARAM, queryString)
//                    .appendQueryParameter(API_KEY, "f8bc1c2f-a416-4927-b866-b05b70de8f11")
//                    .build();
//
//            // Convert the URI to a URL.
//            URL requestURL = new URL(builtURI.toString());
//
//            urlConnection = (HttpURLConnection) requestURL.openConnection();
//            urlConnection.setRequestMethod("GET");
//            urlConnection.connect();
//
//            // Get the InputStream.
//            InputStream inputStream = urlConnection.getInputStream();
//
//            // Create a buffered reader from that input stream.
//            reader = new BufferedReader(new InputStreamReader(inputStream));
//
//            // Use a StringBuilder to hold the incoming response.
//            StringBuilder builder = new StringBuilder();
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                builder.append(line);
//                // Since it's JSON, adding a newline isn't necessary (it won't
//                // affect parsing) but it does make debugging a *lot* easier
//                // if you print out the completed buffer for debugging.
//                builder.append("\n");
//            }
//
//            if (builder.length() == 0) {
//                // Stream was empty. No point in parsing.
//                return null;
//            }
//
//            bookJSONString = builder.toString();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        } finally {
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//
//        // Write the final JSON response to the log
//        Log.d(LOG_TAG, bookJSONString);
//        return bookJSONString;
//    }
//
//}
