package com.applaudostudios.newsapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.applaudostudios.newsapp.data.NewsContract.NewsEntry;
import com.applaudostudios.newsapp.data.NewsContract.SavedNewsEntry;


public class NewsDbHelper extends SQLiteOpenHelper {

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "news.db";

    /**
     * Database version. In case there are changes in the database schema.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of NewsDbHelper.
     *
     * @param context of the app
     */
    NewsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creates a String that contains the SQL statement to create the news table.
        String SQL_CREATES_NEWS_TABLE = "CREATE TABLE " + NewsEntry.TABLE_NAME + " ("
                + NewsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NewsEntry.COLUMN_NEWS_ID + " TEXT, "
                + NewsEntry.COLUMN_NEWS_CATEGORY + " TEXT, "
                + NewsEntry.COLUMN_NEWS_HEADLINE + " TEXT, "
                + NewsEntry.COLUMN_NEWS_BODY_TEXT + " TEXT DEFAULT 'empty', "
                + NewsEntry.COLUMN_NEWS_THUMBNAIL + " TEXT, "
                + NewsEntry.COLUMN_NEWS_WEB_URL + " TEXT);";

        // Creates a String that contains the SQL statement to create the news saved table.
        String SQL_CREATES_SAVED_NEWS_TABLE = "CREATE TABLE " + SavedNewsEntry.TABLE2_NAME + " ("
                + SavedNewsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SavedNewsEntry.COLUMN_NEWS_ID + " TEXT, "
                + SavedNewsEntry.COLUMN_NEWS_HEADLINE + " TEXT, "
                + SavedNewsEntry.COLUMN_NEWS_BODY_TEXT + " TEXT DEFAULT 'empty', "
                + SavedNewsEntry.COLUMN_NEWS_THUMBNAIL + " TEXT, "
                + SavedNewsEntry.COLUMN_NEWS_WEB_URL + " TEXT);";

        // Executes the SQL statements
        db.execSQL(SQL_CREATES_NEWS_TABLE);
        db.execSQL(SQL_CREATES_SAVED_NEWS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}
