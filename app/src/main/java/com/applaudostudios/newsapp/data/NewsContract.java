package com.applaudostudios.newsapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * API contract for the News App.
 */
public final class NewsContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private NewsContract() {
    }

    /**
     * Name for the entire content provider. A convenient string to use for the content authority
     * is the package name for the app, which is guaranteed to be unique on the device.
     */
    public static final String CONTENT_AUTHORITY = "com.applaudostudios.newsapp";

    /**
     * shared by every URI associated with NewsContract.
     */
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Stores the path for the news table which will be appended to the base content URI.
     */
    public static final String PATH_NEWS = "news";

    /**
     * Stores the path for the saved news table which will be appended to the base content URI.
     */
    public static final String PATH_SAVED_NEWS = "saved_news";

    /**
     * Inner class that defines constant values for the news database table.
     * Each entry in the table represents a single news.
     */
    public static final class NewsEntry implements BaseColumns {

        /**
         * The content URI to access the news data in the provider
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NEWS);

        /**
         * The MIME type of the CONTENT_URI for a list of news.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE
                        + "/"
                        + CONTENT_AUTHORITY
                        + PATH_NEWS;

        /**
         * The MIME type of the CONTENT_URI for a single news.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE
                        + "/"
                        + CONTENT_AUTHORITY
                        + PATH_NEWS;

        /**
         * Name of database table for news
         */
        public static final String TABLE_NAME = "news";

        /**
         * Unique ID number for the news item (only for use in the database table).
         *
         * Type: INTEGER
         */
        public static final String _ID = BaseColumns._ID;

        /**
         * Category of the news.
         *
         * The only possible values are CATEGORY_SPORTS, CATEGORY_POLITICS, CATEGORY_BUSINESS,
         * CATEGORY_TECH and CATEGORY_WORLD.
         *
         * Type: TEXT
         */
        public static final String COLUMN_NEWS_CATEGORY = "category";

        /**
         * id of the news.
         *
         * Type: TEXT
         */
        public static final String COLUMN_NEWS_ID = "id";

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
        public static final String COLUMN_NEWS_BODY_TEXT = "body_text";

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

    public static final class SavedNewsEntry implements BaseColumns {

        /**
         * The content URI to access the news data in the provider
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_SAVED_NEWS);

        /**
         * The MIME type of the CONTENT_URI for a single news.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE
                        + "/"
                        + CONTENT_AUTHORITY
                        + PATH_SAVED_NEWS;

        /**
         * Name of database table for the saved news
         */
        public static final String TABLE2_NAME = "saved_news";

        /**
         * Unique ID number for the news item (only for use in the database table).
         *
         * Type: INTEGER
         */
        public static final String _ID = BaseColumns._ID;

        /**
         * id of the news.
         *
         * Type: TEXT
         */
        public static final String COLUMN_NEWS_ID = "id";

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
        public static final String COLUMN_NEWS_BODY_TEXT = "body_text";

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
