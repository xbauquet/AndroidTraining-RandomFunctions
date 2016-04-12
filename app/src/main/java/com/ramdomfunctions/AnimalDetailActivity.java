package com.ramdomfunctions;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

public class AnimalDetailActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor>{

    long id;
    TextView animalId;
    TextView animalName;
    TextView animalSpecies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_detail);
        animalId = (TextView) findViewById(R.id.animal_id);
        animalName = (TextView) findViewById(R.id.animal_name);
        animalSpecies = (TextView) findViewById(R.id.animal_species);

        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id");

        getLoaderManager().initLoader(0, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        Uri uri = ContentUris.withAppendedId(Uri.parse("content://com.ramdomfunctions/animals"), this.id);
        Log.e("URI", uri.toString());
        return new CursorLoader(this, uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();

        while (!data.isAfterLast()){
            animalName.setText(data.getString(data.getColumnIndexOrThrow(Animals.COLUMN_NAME_NAME)));
            animalSpecies.setText(data.getString(data.getColumnIndexOrThrow(Animals.COLUMN_NAME_SPECIES)));
            data.moveToNext();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
