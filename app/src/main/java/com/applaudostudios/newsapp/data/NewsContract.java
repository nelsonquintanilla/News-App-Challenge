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
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Stores the path for this tables which will be appended to the base content URI.
     */
    public static final String PATH_NEWS = "news";

    /**
     * Inner class that defines constant values for the news database table.
     * Each entry in the table represents a single news.
     */
    public static final class NewsEntry implements BaseColumns {

        /**
         * The content URI to access the pet data in the provider
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NEWS);

        /**
         * The MIME type of the CONTENT_URI for a list of pets.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE
                        + "/"
                        + CONTENT_AUTHORITY
                        + PATH_NEWS;

        /**
         * The MIME type of the CONTENT_URI for a single pet.
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
         * Unique ID number for the pet (only for use in the database table).
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

//        /**
//         * Possible values for the category of the news.
//         */
//        public static final int CATEGORY_SPORTS = 0;
//        public static final int CATEGORY_POLITICS = 1;
//        public static final int CATEGORY_BUSINESS = 3;
//        public static final int CATEGORY_TECH = 4;
//        public static final int CATEGORY_WORLD = 5;

    }

}
