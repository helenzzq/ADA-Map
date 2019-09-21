package com.example.uoft_map;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;


import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.common.api.GoogleApiClient;


public class MapController implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private GoogleMap mMap;
    private static final int Request_User_Location_Code = 99;
    public LocationRequest locRequest;
    public Activity mp_act;
    public Loc currentLocation ;
    public Marker previousMarker;

    public void Maps(GoogleMap map, Activity mp_act) {
        this.mMap = map;
        this.mp_act = mp_act;
        this.previousMarker = null;
    }

    public void init_map() {
        if (ContextCompat.checkSelfPermission(mp_act, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(43.6644, -79.3923), 15));
            mMap.setOnMyLocationButtonClickListener(onMyLocationButtonClickListener);
            mMap.setOnMyLocationClickListener(onMyLocationClickListener);
//            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//                @Override
//            public boolean onMarkerClick(Marker marker) {
////                    String locAddress = marker.getTitle();
////                    fillTextViews(locAddress);
//                if (previousMarker != null) {
//                    previousMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//                }
//                previousMarker = marker;
//
//                return true;
//            }
//        });

        }
        else {
            Toast.makeText(mp_act, "TAT...Permission denied", Toast.LENGTH_SHORT).show();
        }
    }



    // rewrite get_location button and set zoom
    private GoogleMap.OnMyLocationButtonClickListener onMyLocationButtonClickListener =
            new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    mMap.setMinZoomPreference(16);
                    return false;
                }
            };

    private GoogleMap.OnMyLocationClickListener onMyLocationClickListener =

            new GoogleMap.OnMyLocationClickListener() {
                @Override
                public void onMyLocationClick(@NonNull Location location) {
                    mMap.setMinZoomPreference(16);
                    CircleOptions circleOptions = new CircleOptions();
                    circleOptions.center(new LatLng(location.getLatitude(),
                            location.getLongitude()));

                    circleOptions.radius(200);
                    circleOptions.fillColor(Color.BLUE);
                    circleOptions.strokeWidth(6);

                    mMap.addCircle(circleOptions);
                }
            };

    public void add_marker() {
        //set marker
        currentLocation = MapsActivity.LC.getCurrentLoaction();
        if (currentLocation != null){
            LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            MarkerOptions markOptions = new MarkerOptions().position(latLng).title(currentLocation.getAbsName());
            previousMarker = mMap.addMarker(markOptions);
        }


    }


    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(mp_act,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(mp_act,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(mp_act,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        Request_User_Location_Code);

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(mp_act,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        Request_User_Location_Code);
            }
            return false;
        } else {
            return true;
        }
    }

    // whenever it's connceted

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locRequest = new LocationRequest();
        //  miliseconds
        locRequest.setInterval(1100);
        locRequest.setFastestInterval(1100);
        locRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
