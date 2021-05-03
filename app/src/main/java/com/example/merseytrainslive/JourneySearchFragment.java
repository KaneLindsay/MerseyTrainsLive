package com.example.merseytrainslive;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
    Dialog dialog;
    List<String> stations;
    Button favouriteButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_journey_search, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        stations = Arrays.asList("Aigburth", "Ainsdale", "Aintree", "Bank Hall", "Bebington", "Bidston", "Birkdale", "Birkenhead Central", "Birkenhead Hamilton Square", "Birkenhead North", "Birkenhead Park", "Blundellsands & Crosby", "Bootle New Strand", "Bootle Oriel Road", "Bromborough", "Bromborough Rake", "Brunswick", "Conway Park", "Cressington", "Eastham Rake", "Fazakerley", "Formby", "Freshfield", "Green Lane", "Hall Road", "Hightown", "Hillside", "Hoylake", "Hunts Cross", "Kirkby", "Kirkdale", "Leasowe", "Liverpool Central", "Liverpool James Street", "Liverpool South Parkway", "Maghull", "Maghull North", "Manor Road", "Meols", "Moorfields", "Moreton", "New Brighton", "Old Road", "Orrell Park", "Port Sunlight", "Rice Lane", "Rock Ferry", "Sandhills", "Seaforth & Litherland", "Southport", "Spital", "St Michaels", "Wallasey Grove Road", "Wallasey Village", "Walton", "Waterloo", "West Kirkby");

        // Setup any handles to view objects here
        departingButton = view.findViewById(R.id.departingButton);
        arrivingButton = view.findViewById(R.id.arrivingButton);
        station1Search = view.findViewById(R.id.station1SearchView);
        station2Search = view.findViewById(R.id.station2SearchView);
        favouriteButton = view.findViewById(R.id.favouriteButton);

        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Favourite> favourites = PrefConfig.readListFromPref(getActivity().getApplicationContext());
                favourites.add(new Favourite(R.drawable.ic_baseline_star, station1Search.getText().toString(), station2Search.getText().toString()));
                PrefConfig.writeListInPref(getActivity().getApplicationContext(), favourites);
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

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, stations);

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

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, stations);

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

    public String[] routeFinder(String startStation, String endStation) {
        String[] transferStations;
        return new String[]{"test"};
    }
}