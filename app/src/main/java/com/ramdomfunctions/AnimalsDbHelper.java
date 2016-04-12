package com.ramdomfunctions;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xavier on 11/04/2016.
 */
public class AnimalsDbHelper extends SQLiteOpenHelper {

    // Bump this for each change in the schema
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Animals.db";

    private static final String SQL_CREATE_ANIMALS =
            "CREATE TABLE " + Animals.TABLE_NAME + " (" +
                    Animals._ID + " INTEGER PRIMARY KEY," +
                    Animals.COLUMN_NAME_ANIMAL_ID + " TEXT," +
                    Animals.COLUMN_NAME_NAME + " TEXT," +
                    Animals.COLUMN_NAME_SPECIES + " TEXT )";

    public AnimalsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ANIMALS);
    }

    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS "
            + Animals.TABLE_NAME;

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
// This database upgrade policy is to discard the data
        db.execSQL(SQL_DROP_TABLE);
        onCreate(db);
    }


}
