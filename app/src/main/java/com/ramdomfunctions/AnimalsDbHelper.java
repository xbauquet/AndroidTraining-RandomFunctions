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
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Animals.db";

    private static final String SQL_CREATE_ANIMALS =
            "CREATE TABLE " + Animals.TABLE_NAME + " (" +
                    Animals._ID + " INTEGER PRIMARY KEY," +
                    Animals.COLUMN_PHOTO_URL + " TEXT," +
                    Animals.COLUMN_NAME_NAME + " TEXT," +
                    Animals.COLUMN_NAME_SPECIES + " TEXT )";

    public AnimalsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ANIMALS);

        ContentValues simba = new ContentValues();
        simba.put(Animals.COLUMN_NAME_NAME, "Simba");
        simba.put(Animals.COLUMN_NAME_SPECIES, "Lion");
        simba.put(Animals.COLUMN_PHOTO_URL, "http://disney-planet.fr/wp-content/uploads/2015/01/image-simba-personnage-roi-lion-05.jpg");

        ContentValues pumba = new ContentValues();
        pumba.put(Animals.COLUMN_NAME_NAME, "Pumba");
        pumba.put(Animals.COLUMN_NAME_SPECIES, "Sanglier");
        pumba.put(Animals.COLUMN_PHOTO_URL, "http://3.bp.blogspot.com/-wKilxS2dnm4/UE_F_7d5nCI/AAAAAAAADtk/MG9i5NTqZFE/s1600/Papel+de+Parede+Tim%25C3%25A3o+e+Pumba.jpg");

        ContentValues timon = new ContentValues();
        timon.put(Animals.COLUMN_NAME_NAME, "Timon");
        timon.put(Animals.COLUMN_NAME_SPECIES, "Suricate");
        timon.put(Animals.COLUMN_PHOTO_URL, "http://images5.fanpop.com/image/photos/27100000/timoan-go-hawaian-timon-27137410-1150-672.jpg");

        ContentValues nala = new ContentValues();
        nala.put(Animals.COLUMN_NAME_NAME, "Nala");
        nala.put(Animals.COLUMN_NAME_SPECIES, "Lion");
        nala.put(Animals.COLUMN_PHOTO_URL, "http://p5.storage.canalblog.com/59/67/183178/103952164_o.png");

        ContentValues rafiki = new ContentValues();
        rafiki.put(Animals.COLUMN_NAME_NAME, "Rafiki");
        rafiki.put(Animals.COLUMN_NAME_SPECIES, "Mandrill");
        rafiki.put(Animals.COLUMN_PHOTO_URL, "https://i.ytimg.com/vi/mjYGB4uYgWw/maxresdefault.jpg");

        ContentValues zazu = new ContentValues();
        zazu.put(Animals.COLUMN_NAME_NAME, "Zazu");
        zazu.put(Animals.COLUMN_NAME_SPECIES, "Calao");
        zazu.put(Animals.COLUMN_PHOTO_URL, "http://www.lecinemaestpolitique.fr/wp-content/uploads/2012/08/roi-lion-10.jpg");

        db.beginTransaction();
        try {
            db.insert(Animals.TABLE_NAME, null, simba);
            db.insert(Animals.TABLE_NAME, null, pumba);
            db.insert(Animals.TABLE_NAME, null, timon);
            db.insert(Animals.TABLE_NAME, null, nala);
            db.insert(Animals.TABLE_NAME, null, zazu);
            db.insert(Animals.TABLE_NAME, null, rafiki);
            db.setTransactionSuccessful();
        } finally {

            db.endTransaction();
        }

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
