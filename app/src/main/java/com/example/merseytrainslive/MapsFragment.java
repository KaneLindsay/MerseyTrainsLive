package com.example.merseytrainslive;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polyline;

import org.jetbrains.annotations.NotNull;


public class MapsFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    public MapsFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        return inflater.inflate(R.layout.activity_maps, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);  //starts map download - asynchronous

    }


    /**
     * Manipulates the map once available.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {   //handle markers on map in here

        //###################################################################################
        //edit bound_image stuff + the stuff in .position to size the image to fit the screen
        //can also edit the image bounds to make it able to pan/zoom more
        //###################################################################################

        googleMap.setOnMarkerClickListener(this);

        //set bounds for the pan
        final LatLng BOUND_CORNER_NW = new LatLng(56.9, -23.5); //53.66, -3.293706
        final LatLng BOUND_CORNER_SE = new LatLng(56.45, -22.7); //53.224228, -2.708257
        final LatLngBounds RESTRICTED_BOUNDS_AREA = new LatLngBounds.Builder()
                .include(BOUND_CORNER_NW)
                .include(BOUND_CORNER_SE)
                .build();

        //set bounds for image overlay
        final LatLng BOUND_IMAGE_NW = new LatLng(56.723055, -23.123434);
//        final LatLng BOUND_IMAGE_SE = new LatLng(53.224228, -2.708257);
//        final LatLngBounds IMAGE_BOUNDS_AREA = new LatLngBounds.Builder()
//                .include(BOUND_CORNER_NW)
//                .include(BOUND_CORNER_SE)
//                .build();


        //restrict zoom bounds + pan bounds
        googleMap.setLatLngBoundsForCameraTarget(RESTRICTED_BOUNDS_AREA); //set bounds for pan
        googleMap.setMinZoomPreference(9.5f); // Set a preference for minimum zoom (Zoom out).
        googleMap.setMaxZoomPreference(13.0f); // Set a preference for maximum zoom (Zoom In).

        final LatLng kirkdale = new LatLng(56.66055, -23.048887);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(kirkdale));

        // ###################################################################################
        // ###################################################################################


        //put overlay on map - can edit size
        GroundOverlayOptions image = new GroundOverlayOptions()
                .image(bitmapDescriptorFromVector(getContext(), R.drawable.ic_transparent_bg))
                .position(BOUND_IMAGE_NW, 50000f, 70000f);
                //.positionFromBounds(IMAGE_BOUNDS_AREA);
        googleMap.addGroundOverlay(image);



        // ###################################################################################
        // ###################################################################################


        //calls controller to put all markers down
        Context context = getContext();
        MapMarkerController mapMarkers = new MapMarkerController(googleMap, context, googleMap);

        //station.setVisible(false);



        //polyline listener
        googleMap.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener()
        {
            @Override
            public void onPolylineClick(Polyline polyline)
            {
                //do something with polyline
                //make a track class
                //has first station coords
                //has 2nd station coords
                //stores what trains are present

                //onclick searcheds for a track object with matching 1st/2nd coords
                //then displays the info of trains on that track section

                //polyline1.setColor(111);
            }
        });
    }


    //create overlay from svg image to bitmap image
    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId){
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0,0,vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    @Override
    public boolean onMarkerClick(@NotNull Marker marker) {
        FragmentTransaction map = getFragmentManager().beginTransaction();
        BottomSheetDialog newFragment = BottomSheetDialog.newInstance(marker.getTitle());
        newFragment.show(map, "dialog");
        return false;
    }
}
