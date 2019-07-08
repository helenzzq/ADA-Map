package com.example.uoft_map;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;

import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Map;
//import com.google.android.gms.maps.model.LatLngBounds;

public class MapController implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private GoogleMap mMap;
    private static final int Request_User_Location_Code = 99;
    public LocationRequest locRequest;
    public Activity mp_act;
    public Loc currentLocation;

    public void Maps(GoogleMap map, Loc curr, Activity mp_act) {
        this.mMap = map;
        this.currentLocation = curr;
        this.mp_act = mp_act;

    }


    //ToDo: 1. 目的：改良code structure。 创建一个MapController 的Java 并且把所有调用google map的function放进去.
    //ToDo: 2. 目的：处理搜索结果。在搜索结果出来之后，处理data。
    //todo 2.1 -- 根据搜索界面返回的数值创建Loc，或者直接获取Loc Class 具体传递方法未定。

    //todo 2.2 -- 在Loc 有了的情况下，根据 Loc 的坐标 设置一个marker 并显示出来。

    public void init_map() {
        if (ContextCompat.checkSelfPermission(mp_act, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(43.6644, -79.3923), 15));
            mMap.setOnMyLocationButtonClickListener(onMyLocationButtonClickListener);
            mMap.setOnMyLocationClickListener(onMyLocationClickListener);

        } else {
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
        MarkerOptions markerOptions = new MarkerOptions();
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        mMap.addMarker(markerOptions.position(latLng).title(currentLocation.getAbsName()));
    }


/*
    public void setLocationFragment(Loc location){
        //ToDo 3： 在搜索返回后，在用户点击了地图上的图标时（如BA 的marker）invoke 此function 并且把Loc class 传递进去此fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.LocationFrame, new MapLocationFragment());
        transaction.commit();
    }
*/


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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Request_User_Location_Code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(mp_act, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(mp_act, "TAT...Permission denied", Toast.LENGTH_SHORT).show();
                }
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
