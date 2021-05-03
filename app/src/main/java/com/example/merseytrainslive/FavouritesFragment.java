package com.example.merseytrainslive;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class FavouritesFragment extends Fragment {

    ArrayList<Favourite> favourites;
    FavouriteAdapter adapter;

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

        favourites = PrefConfig.readListFromPref(getActivity().getApplicationContext());
        if (favourites == null) {
            favourites = new ArrayList<>();
        }

        recyclerView.setLayoutManager(layoutManager);
        adapter = new FavouriteAdapter(favourites);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new FavouriteAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    public void removeItem(int position) {
        favourites.remove(position);
        adapter.notifyItemRemoved(position);
        PrefConfig.writeListInPref(getActivity().getApplicationContext(), favourites);
    }
}