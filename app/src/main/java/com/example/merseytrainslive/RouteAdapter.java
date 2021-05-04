package com.example.merseytrainslive;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteViewHolder> {

    private ArrayList<Station> stationList;
    String changeText = "Change Here";
    Station changeStation;

    public static class RouteViewHolder extends RecyclerView.ViewHolder {

        public TextView stationName;
        public TextView changeNotify;

        public RouteViewHolder(View itemView) {
            super(itemView);
            stationName = itemView.findViewById(R.id.stationName);
            changeNotify = itemView.findViewById(R.id.changeNotify);
        }

    }

    public RouteAdapter(ArrayList<Station> stationList, Station changeStation) {
        this.stationList = stationList;
        this.changeStation = changeStation;
    }

    @NotNull
    @Override
    public RouteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.station_item, parent, false);
        return new RouteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RouteViewHolder holder, int position) {
        Station currentStation = stationList.get(position);
        holder.stationName.setText(currentStation.getStationName());
        if (stationList.get(position).getStanox() == changeStation.getStanox()) {
            holder.changeNotify.setText(changeText);
        }
    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }

}
