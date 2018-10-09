package com.applaudostudios.newsapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.applaudostudios.newsapp.data.NewsContract.NewsEntry;

public class NewsProvider extends ContentProvider {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = NewsProvider.class.getSimpleName();

    /**
     * URI matcher code for the content URI for the news table
     */
    public static final int NEWS = 100;

    /**
     * URI matcher code for the content URI for a single news in the news table
     */
    public static final int NEWS_ID = 101;

//    /**
//     * URI matcher code for the content URI for the news table
//     */
//    public static final int NEWS_CATEGORY = 102;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // This URI is used to provide access to MULTIPLE rows of the news table.
        sUriMatcher.addURI(NewsContract.CONTENT_AUTHORITY, NewsContract.PATH_NEWS, NEWS);

        // This URI is used to provide access to ONE single row of the pets table.
        sUriMatcher.addURI(NewsContract.CONTENT_AUTHORITY, NewsContract.PATH_NEWS + "/#", NEWS_ID);

//        // This URI is used to provide access to MULTIPLE rows of the same category in the
//        // the news table.
//        sUriMatcher.addURI(NewsContract.CONTENT_AUTHORITY, NewsContract.PATH_NEWS, NEWS_CATEGORY);
    }

    /**
     * Database helper object
     */
    private NewsDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new NewsDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(
            @NonNull Uri uri,
            @Nullable String[] projection,
            @Nullable String selection,
            @Nullable String[] selectionArgs,
            @Nullable String sortOrder) {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = null;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case NEWS:
                cursor = db.query(NewsEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;

            case NEWS_ID:
                selection = NewsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(NewsEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;

            default:
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        switch (match) {
            case NEWS:
                long id = db.insert(NewsEntry.TABLE_NAME, null, contentValues);
                // If the ID is -1, then the insertion failed. Log an error and return null.
                if (id == -1) {
                    Log.e(LOG_TAG, "Failed to insert row for " + uri);
                    return null;
                }
                // Return the new URI with the ID (of the newly inserted row) appended at the end.
                return ContentUris.withAppendedId(uri, id);

            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    @Override
    public int update(
            @NonNull Uri uri,
            @Nullable ContentValues contentValues,
            @Nullable String selection,
            @Nullable String[] selectionArgs) {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        switch (match) {
            case NEWS:
                return db.update(NewsEntry.TABLE_NAME, contentValues, selection, selectionArgs);

            case NEWS_ID:
                selection = NewsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return db.update(NewsEntry.TABLE_NAME, contentValues, selection, selectionArgs);

            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        switch (match) {
            case NEWS:
                return db.delete(NewsEntry.TABLE_NAME, selection, selectionArgs);

            case NEWS_ID:
                selection = NewsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return db.delete(NewsEntry.TABLE_NAME, selection, selectionArgs);

            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
    }

    /**
     * Returns a string that describes the type of the data stored at the input iri (MIME type).
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case NEWS:
                return NewsEntry.CONTENT_LIST_TYPE;
            case NEWS_ID:
                return NewsEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }


}
