package com.example.merseytrainslive;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView station1Search;
    TextView station2Search;
    String stringy = "hello";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        station1Search = findViewById(R.id.station1SearchView);
        station2Search = findViewById(R.id.station2SearchView);

    }

    public void timetableTabClick(View v) {
        View b = findViewById(R.id.mapButton);
        View c = findViewById(R.id.favouriteButton);
        v.setEnabled(false);
        c.setEnabled(true);
        b.setEnabled(true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        JourneySearchFragment journeySearchFragment = new JourneySearchFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment, journeySearchFragment, null)
                .setReorderingAllowed(true)
                .addToBackStack("name2") // name can be null
                .commit();
    }

    public void mapTabClick(View v) {
        View b = findViewById(R.id.timetableButton);
        View c = findViewById(R.id.favouriteButton);
        v.setEnabled(false);
        b.setEnabled(true);
        c.setEnabled(true);
    }

    public void favouritesTabClick(View v) {
        View b = findViewById(R.id.timetableButton);
        View c = findViewById(R.id.mapButton);
        v.setEnabled(false);
        b.setEnabled(true);
        c.setEnabled(true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FavouritesFragment favouritesFragment = new FavouritesFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment, favouritesFragment, null)
                .setReorderingAllowed(true)
                .addToBackStack("name") // name can be null
                .commit();
    }

}