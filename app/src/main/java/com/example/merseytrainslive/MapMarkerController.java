package com.example.merseytrainslive;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapMarkerController {
    public String[] stationName = {"James Street", "Lime Street", "Moorfields", "Liverpool Central",
            //sandhills and up
            "Sandhills", "Bank Hall", "Bootle Oriel Road", "Bootle New Strand", "Seaforth & LitherLand", "Waterloo", "Blundellsands & Crosby", "Hall Road",
            "Hightown", "Formby", "Freshfield", "Ainsdale", "Hillside", "Birkdale", "Southport",
            //brunswick and below
            "Brunswick", "St. Michaels", "Aigburth",  "Cressington", "Liverpool South Parkway", "Hunts Cross",
            //kirkdale and right
            "Kirkdale", "Rice Lane", "Fazakerley", "Kirkby",
            //walton and up/right
            "Walton", "Orrell Park", "Aintree", "Old Roan", "Maghull", "Maghull North", "Town Green", "Aughton Park",
            "Ormskirk",

            "Hamilton Square", "Conway Park", "Birkenhead Park", "Birkinhead North", "Wallasey Village", "Wallasey Grove Road", "New Brighton",
            "Bidston", "Leasowe", "Moreton", "Moels", "Manor Road","Hoylake", "West Kirby",
            "Birkenhead Central", "Green Lane", "Rock Ferry", "Bebington", "Port Sunlight", "Spital", "Bromborough Rake","Bromborough", "Eastham Rake", "Hooton", "Little Sutton", "Overpool", "Ellesmere Port",
            "Capenhurst", "Bache", "Chester"
    };

    public double[][] stationCoords =
            //####     town lines     #####
            {{56.62055, -23.123434}, {56.62055, -23.03434}, {56.64055, -23.078887}, {56.60055, -23.078887},
             //####     northern line     #####
             // sandhills and up
            {56.66055, -23.078887}, {56.68055, -23.078887}, {56.70055, -23.078887},
            {56.72055, -23.078887}, {56.74055, -23.078887}, {56.76055, -23.078887}, {56.78055, -23.078887},
            {56.80055, -23.078887}, {56.82055, -23.078887}, {56.84055, -23.078887}, {56.86055, -23.078887},
            {56.88055, -23.0531727143}, {56.90055, -23.0274584286}, {56.92055, -23.0017441428}, {56.94055, -22.9760298571},
            //brunswick and below
            {56.58055, -23.078887}, {56.5655, -23.0531727143}, {56.5655, -23.0231727143}, {56.5655, -22.9931727143},
            {56.5655, -22.9331727143}, {56.5655, -22.9031727143},
            //kirkdale and right (rice lane, fazakerley, kirkby)
            {56.66055, -23.048887}, {56.66055, -23.018887}, {56.66055, -22.988887}, {56.66055, -22.95887},
            //walton and up/right
            {56.68055, -23.018887}, {56.70055, -22.9931727143}, {56.72055, -22.9674584286}, {56.74055, -22.9417441429},
            {56.76055, -22.9160298572}, {56.78055, -22.8903155715}, {56.80055, -22.8646012858}, {56.82055, -22.8388870001},
            {56.84055, -22.8131727144},

            //####     wirral line     #####
            //new brighton
            {56.62055, -23.20434},{56.64055, -23.25434},{56.68055, -23.25434}, {56.720055, -23.25434}, {56.760055, -23.25434},
            {56.80055, -23.25434}, {56.84055, -23.25434},
            //west kirby
            {56.72055, -23.34434},{56.78055, -23.34434}, {56.84055, -23.34434}, {56.84055, -23.4434}, {56.78055, -23.4434},
            {56.74055, -23.4434},{56.7055, -23.4434},
            //birkenhead central to hooton
            {56.5955, -23.25434}, {56.5655, -23.25434}, {56.5355, -23.25434}, {56.5055, -23.25434},
            {56.5055, -23.20434},{56.5055, -23.15434}, {56.5055, -23.10434},{56.5055, -23.05434} ,
            {56.5055, -23.00434}, {56.5055, -22.95434},
            //little sutton to Ellesmere Port
            {56.5055, -22.90434}, {56.5055, -22.85434}, {56.5055, -22.80434},
             //Capenhurst to chester
            {56.4855, -22.95434}, {56.4655, -22.95434}, {56.4455, -22.95434}
            };


    private Marker[] stationMarkers = new Marker[stationName.length];

    public MapMarkerController(GoogleMap mMap, Context context, GoogleMap googleMap){
        //makes all markers
        for (int i = 0; i < stationName.length; i++){

            LatLng coords = new LatLng(stationCoords[i][0], stationCoords[i][1]);
            stationMarkers[i] = mMap.addMarker(new MarkerOptions()
                    .position(coords)
                    .title(stationName[i])
                    .icon(bitmapDescriptorFromVector(context, R.drawable.ic_circle2)));
        }

        //makes all the tracks + colours them
        for (int i = 0; i < stationCoords.length-1; i++){
            if (stationName[i] != "Ellesmere Port" && stationName[i] != "Chester"&& stationName[i] != "New Brighton" &&
                    stationName[i] != "West Kirby" && stationName[i] != "James Street" && stationName[i] != "Ormskirk"
                    && stationName[i] != "Southport" &&  stationName[i] != "Kirkby" && stationName[i] != "Hunts Cross") {

                if (i < 38 && i > 2){
                Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .color(Color.BLUE)
                        .add(
                                new LatLng(stationCoords[i][0], stationCoords[i][1]),
                                new LatLng(stationCoords[i + 1][0], stationCoords[i + 1][1])));
                }
                else {
                    Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .color(Color.GREEN)
                            .add(
                                    new LatLng(stationCoords[i][0], stationCoords[i][1]),
                                    new LatLng(stationCoords[i + 1][0], stationCoords[i + 1][1])));
                }
            }
        }
        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.GREEN)
                .add(
                        //james street to hamilton
                        new LatLng(stationCoords[0][0], stationCoords[0][1]),
                        new LatLng(stationCoords[38][0], stationCoords[38][1])
                ));
        Polyline polyline2 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.GREEN)
                .add(
                        //james street to moorfields
                        new LatLng(stationCoords[0][0], stationCoords[0][1]),
                        new LatLng(stationCoords[2][0], stationCoords[2][1])
                ));
        Polyline polyline3 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.GREEN)
                .add(
                        //james street to central
                        new LatLng(stationCoords[0][0], stationCoords[0][1]),
                        new LatLng(stationCoords[3][0], stationCoords[3][1])
                ));
        Polyline polyline4 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.GREEN)
                .add(
                        //lime street to central
                        new LatLng(stationCoords[1][0], stationCoords[1][1]),
                        new LatLng(stationCoords[3][0], stationCoords[3][1])
                ));
        Polyline polyline5 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.BLUE)
                .add(
                        //sandhills to kirkdale
                        new LatLng(stationCoords[4][0], stationCoords[4][1]),
                        new LatLng(stationCoords[25][0], stationCoords[25][1])
                ));
        Polyline polyline6 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.BLUE)
                .add(
                        //kirkdale to walton
                        new LatLng(stationCoords[25][0], stationCoords[25][1]),
                        new LatLng(stationCoords[29][0], stationCoords[29][1])
                ));
        Polyline polyline7 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.BLUE)
                .add(
                        //central to brunswick
                        new LatLng(stationCoords[3][0], stationCoords[3][1]),
                        new LatLng(stationCoords[19][0], stationCoords[19][1])
                ));
        Polyline polyline8 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.GREEN)
                .add(
                        //hooten to capenhurst
                        new LatLng(stationCoords[61][0], stationCoords[61][1]),
                        new LatLng(stationCoords[65][0], stationCoords[65][1])
                ));
        Polyline polyline9 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.GREEN)
                .add(
                        //Hamilton to birkenhead central
                        new LatLng(stationCoords[38][0], stationCoords[38][1]),
                        new LatLng(stationCoords[52][0], stationCoords[52][1])
                ));
        Polyline polyline10 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .color(Color.GREEN)
                .add(
                        //Birkenhead north to bidston
                        new LatLng(stationCoords[41][0], stationCoords[41][1]),
                        new LatLng(stationCoords[45][0], stationCoords[45][1])
                ));
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
