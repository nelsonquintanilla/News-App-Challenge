package com.applaudostudios.newsapp.data;

import android.provider.BaseColumns;

/**
 * API contract for the News App.
 */
public final class NewsContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private NewsContract() {}

    /**
     * Inner class that defines constant values for the news database table.
     * Each entry in the table represents a single news.
     */
    public static final class NewsEntry implements BaseColumns{

        /** Name of database table for news */
        public static final String TABLE_NAME = "news";

        /**
         * Unique ID number for the pet (only for use in the database table).
         *
         * Type: INTEGER
         */
        public static final String _ID = BaseColumns._ID;

        /**
         * Headline of the news.
         *
         * Type: TEXT
         */
        public static final String COLUMN_NEWS_HEADLINE = "headline";

        /**
         * Body text of the news.
         *
         * Type: TEXT
         */
        public static final String COLUMN_NEWS_BODYTEXT = "body_text";

        /**
         * Thumbnail of the news.
         *
         * Type: TEXT
         */
        public static final String COLUMN_NEWS_THUMBNAIL = "thumbnail";

        /**
         * Web url of the news.
         *
         * Type: TEXT
         */
        public static final String COLUMN_NEWS_WEB_URL = "web_url";

    }

}
