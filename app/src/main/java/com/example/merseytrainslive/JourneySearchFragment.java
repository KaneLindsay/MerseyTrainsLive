package com.example.merseytrainslive;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JourneySearchFragment extends Fragment {

    TextView station1Search;
    TextView station2Search;
    Button departingButton;
    Button arrivingButton;
    Button searchButton;
    Dialog dialog;
    List<String> stationNames;
    Button favouriteButton;
    RouteAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_journey_search, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        stationNames = Arrays.asList("Aigburth", "Ainsdale", "Aintree", "Bank Hall", "Bebington", "Bidston", "Birkdale", "Birkenhead Central", "Birkenhead Hamilton Square", "Birkenhead North", "Birkenhead Park", "Blundellsands & Crosby", "Bootle New Strand", "Bootle Oriel Road", "Bromborough", "Bromborough Rake", "Brunswick", "Conway Park", "Cressington", "Eastham Rake", "Fazakerley", "Formby", "Freshfield", "Green Lane", "Hall Road", "Hightown", "Hillside", "Hoylake", "Hunts Cross", "Kirkby", "Kirkdale", "Leasowe", "Liverpool Central", "Liverpool James Street", "Liverpool South Parkway", "Maghull", "Maghull North", "Manor Road", "Meols", "Moorfields", "Moreton", "New Brighton", "Old Road", "Orrell Park", "Port Sunlight", "Rice Lane", "Rock Ferry", "Sandhills", "Seaforth & Litherland", "Southport", "Spital", "St Michaels", "Wallasey Grove Road", "Wallasey Village", "Walton", "Waterloo", "West Kirkby");

        // Setup any handles to view objects here
        departingButton = view.findViewById(R.id.departingButton);
        arrivingButton = view.findViewById(R.id.arrivingButton);
        station1Search = view.findViewById(R.id.station1SearchView);
        station2Search = view.findViewById(R.id.station2SearchView);
        favouriteButton = view.findViewById(R.id.favouriteButton);
        searchButton = view.findViewById(R.id.searchButton);

        RecyclerView.LayoutManager layoutManager;
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteSetup route = new RouteSetup();
                PathCalculation p = new PathCalculation(route.chesterRoute[9], route.southPortRoute[22]);
                Station[] routeArray = p.findRoute();

                ArrayList<Station> routeArrayList = new ArrayList<>(Arrays.asList(routeArray));
                System.out.println(routeArrayList);

                adapter = new RouteAdapter(routeArrayList);
                recyclerView.setAdapter(adapter);

            }
        });

        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Favourite> favourites = PrefConfig.readListFromPref(getActivity());
                // Create a new ArrayList to add to if there are no favourites already.
                if (favourites == null) {
                    favourites = new ArrayList<>();
                }
                favourites.add(new Favourite(R.drawable.ic_baseline_stars_24, station1Search.getText().toString(), station2Search.getText().toString()));
                PrefConfig.writeListInPref(getActivity(), favourites);
            }
        });

        departingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = view.findViewById(R.id.arrivingButton);
                v.setEnabled(false);
                b.setEnabled(true);
            }
        });

        arrivingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = view.findViewById(R.id.departingButton);
                v.setEnabled(false);
                b.setEnabled(true);
            }
        });

        // Display search dialog on clicking a search bar.
        station1Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_searchable_spinner);
                dialog.getWindow().setLayout(650, 800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView = dialog.findViewById(R.id.list_view);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, stationNames);

                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        station1Search.setText(adapter.getItem(position));
                        dialog.dismiss();
                    }
                });
            }
        });

        station2Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_searchable_spinner);
                dialog.getWindow().setLayout(650, 800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView = dialog.findViewById(R.id.list_view);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, stationNames);

                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        station2Search.setText(adapter.getItem(position));
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}