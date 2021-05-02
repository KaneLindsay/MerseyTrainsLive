package com.example.merseytrainslive;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FavouritesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false);

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager layoutManager;
        // Setup any handles to view objects here
        ArrayList<Favourite> favourites = new ArrayList<>();

        favourites.add(new Favourite(R.drawable.ic_baseline_star, "Hunts Cross", "Southport"));
        favourites.add(new Favourite(R.drawable.ic_baseline_star, "Liverpool Central", "Ormskirk"));
        favourites.add(new Favourite(R.drawable.ic_baseline_star, "Liverpool Central", "Kirkby"));

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new FavouriteAdapter(favourites);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}