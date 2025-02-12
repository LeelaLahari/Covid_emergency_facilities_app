package com.example.helpinghands_is;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.google.android.gms.maps.GoogleMap;

public class ShowOxygen extends AppCompatActivity {
    private GoogleMap mMap;

    // creating a variable
    // for search view.
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri gmmIntentUri = Uri.parse("geo:0,0?q=Oxygen centers nearby");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mapIntent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Intent intent = new Intent(getBaseContext(),MainActivity.class);
        startActivity(intent);
    }
}