package com.example.mapoverlay;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapMarkerController {
    public String[] stationName = {"Formby", "Freshfield"};
    public double[][] stationCoords = {{53.4, -3.1}, {53.4, -3.0}};
    private Marker[] stationMarkers = new Marker[stationName.length];

    public MapMarkerController(GoogleMap mMap, Context context){
        for (int i = 0; i < stationName.length; i++){

            LatLng coords = new LatLng(stationCoords[i][0], stationCoords[i][1]);
            stationMarkers[i] = mMap.addMarker(new MarkerOptions()
                    .position(coords)
                    .title(stationName[i])
                    .icon(bitmapDescriptorFromVector(context, R.drawable.ic_baseline_train_24)));
        }
    }


    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId){
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0,0,vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);

    }


}
