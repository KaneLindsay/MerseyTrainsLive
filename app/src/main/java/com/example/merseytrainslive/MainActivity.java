package com.example.merseytrainslive;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements BottomSheetDialog.BottomSheetListener {

    TextView station1Search;
    TextView station2Search;
    JourneySearchFragment journeySearchFragment = new JourneySearchFragment();
    FavouritesFragment favouritesFragment = new FavouritesFragment();
    MapsActivity mapsActivity = new MapsActivity();

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
        changeFragment(journeySearchFragment);
    }

    public void mapTabClick(View v) {
        View b = findViewById(R.id.timetableButton);
        View c = findViewById(R.id.favouriteButton);
        v.setEnabled(false);
        b.setEnabled(true);
        c.setEnabled(true);
        changeFragment(mapsActivity);
    }

    public void favouritesTabClick(View v) {
        View b = findViewById(R.id.timetableButton);
        View c = findViewById(R.id.mapButton);
        v.setEnabled(false);
        b.setEnabled(true);
        c.setEnabled(true);
        changeFragment(favouritesFragment);
    }

    private void changeFragment(Fragment fr){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment, fr, null)
                .setReorderingAllowed(true)
                .commit();
    }

    @Override
    public void onButtonClicked(String text) {

    }
}