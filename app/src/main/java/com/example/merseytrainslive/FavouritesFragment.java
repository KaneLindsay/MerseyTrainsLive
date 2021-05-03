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

    ArrayList<Favourite> favourites = new ArrayList<>();
    FavouriteAdapter adapter = new FavouriteAdapter(favourites);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false);

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;
        // Setup any handles to view objects here
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());

        adapter.setOnItemClickListener(new FavouriteAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    public void addItem(String station1, String station2) {
        favourites.add(new Favourite(R.drawable.ic_baseline_star, station1, station2));
        adapter.notifyItemInserted(favourites.size());
    }

    public void removeItem(int position) {
        favourites.remove(position);
        adapter.notifyItemRemoved(position);
    }
}