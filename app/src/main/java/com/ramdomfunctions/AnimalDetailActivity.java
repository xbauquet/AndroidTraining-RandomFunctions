package com.ramdomfunctions;

import android.Manifest;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;

public class AnimalDetailActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    long id;
    TextView animalName;
    TextView animalSpecies;
    ImageView animalImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animal_detail);

        animalName = (TextView) findViewById(R.id.animal_name);
        animalSpecies = (TextView) findViewById(R.id.animal_species);
        animalImage = (ImageView) findViewById(R.id.animal_image);

        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id");
        verifyPermissions(this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        Uri uri = ContentUris.withAppendedId(Uri.parse("content://com.ramdomfunctions/animals"), this.id);
        return new CursorLoader(this, uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();

        while (!data.isAfterLast()) {
            Log.e("URL", data.getString(data.getColumnIndexOrThrow(Animals.COLUMN_PHOTO_URL)));
            Picasso.with(this).load(data.getString(data.getColumnIndexOrThrow(Animals.COLUMN_PHOTO_URL))).placeholder(R.drawable.blank).into(animalImage);
            animalName.setText(data.getString(data.getColumnIndexOrThrow(Animals.COLUMN_NAME_NAME)));
            animalSpecies.setText(data.getString(data.getColumnIndexOrThrow(Animals.COLUMN_NAME_SPECIES)));
            data.moveToNext();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getLoaderManager().initLoader(0, null, this);
        }
    }

    private static final int REQUEST_PERMISSION = 1;
    private static String[] PERMISSIONS = { Manifest.permission.INTERNET };

    public void verifyPermissions(Activity activity) {
        // Check if we have permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.INTERNET);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS, REQUEST_PERMISSION);
        } else {
            // Prepare the loader. Either re-connect with an existing one,
            // or start a new one.
            getLoaderManager().initLoader(0, null, this);
        }
    }

}
