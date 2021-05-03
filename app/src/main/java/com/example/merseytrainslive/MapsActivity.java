package com.example.merseytrainslive;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;
import com.google.android.gms.maps.model.UrlTileProvider;

import java.net.MalformedURLException;
import java.net.URL;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);  //starts map download - asynchronous
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMapReady(GoogleMap googleMap) {   //handle markers on map in here

        mMap = googleMap;

        //###################################################################################
        //edit bound_image stuff + the stuff in .position to size the image to fit the screen
        //can also edit the image bounds to make it able to pan/zoom more
        //###################################################################################

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
        mMap.setLatLngBoundsForCameraTarget(RESTRICTED_BOUNDS_AREA); //set bounds for pan
        mMap.setMinZoomPreference(9.5f); // Set a preference for minimum zoom (Zoom out).
        mMap.setMaxZoomPreference(13.0f); // Set a preference for maximum zoom (Zoom In).

        final LatLng kirkdale = new LatLng(56.66055, -23.048887);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kirkdale));

        // ###################################################################################
        // ###################################################################################


        //put overlay on map - can edit size
        GroundOverlayOptions image = new GroundOverlayOptions()
                .image(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_transparent_bg))
                .position(BOUND_IMAGE_NW, 50000f, 70000f);
                //.positionFromBounds(IMAGE_BOUNDS_AREA);
        mMap.addGroundOverlay(image);



        // ###################################################################################
        // ###################################################################################


        //calls controller to put all markers down
        Context context = getApplicationContext();
        MapMarkerController mapMarkers = new MapMarkerController(mMap, context, googleMap);

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

}
