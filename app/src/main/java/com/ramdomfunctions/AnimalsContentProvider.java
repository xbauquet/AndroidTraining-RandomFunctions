package com.ramdomfunctions;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.util.List;

public class AnimalsContentProvider extends ContentProvider {


    // URI matcher
    private static final String AUTHORITY = "com.ramdomfunctions";
    private static final String ANIMALS_PATH = "animals";
    private static final int ANIMALS_LIST = 0;
    private static final int ANIMALS_DETAILS = 1;
    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        matcher.addURI(AUTHORITY, ANIMALS_PATH, ANIMALS_LIST);
        matcher.addURI(AUTHORITY, ANIMALS_PATH + "/#", ANIMALS_DETAILS);
    }



    public AnimalsContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        AnimalsDbHelper helper = new AnimalsDbHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        // Create a new map of values, where column names are the keys





        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        AnimalsDbHelper helper = new AnimalsDbHelper(getContext());
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor result;
        int uriType = matcher.match(uri);
        switch (uriType) {
            case ANIMALS_LIST: {

                result = db.query(
                        Animals.TABLE_NAME, // The table to query
                        projection, // The columns to return, null = ALL
                        null, // WHERE clause, using ?s for values
                        null, // The values for the WHERE clause
                        null, // GROUP BY clause (rows grouping)
                        null, // HAVING clause (group filtering)
                        null // ORDER BY clause
                );
                break;

            }
            case ANIMALS_DETAILS: {
                String[] id = { uri.getLastPathSegment() };

                result = db.query(
                        Animals.TABLE_NAME, // The table to query
                        projection, // The columns to return, null = ALL
                        "_ID = ?", // WHERE clause, using ?s for values
                        id, // The values for the WHERE clause
                        null, // GROUP BY clause (rows grouping)
                        null, // HAVING clause (group filtering)
                        null // ORDER BY clause
                );
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown URI: " + uri);
            }
        }
        result.setNotificationUri(getContext().getContentResolver(), uri);
        return result;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
