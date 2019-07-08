package com.example.uoft_map;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.maps.model.LatLngBounds;





public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener
{

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locRequest;
    private static final int Request_User_Location_Code = 99;
    public static LocsController LC = new LocsController();
    public static MapController MC = new MapController();
    public Loc currentLocation = LC.getCurrentLoaction();

    //ToDo: 1. 目的：改良code structure。 创建一个MapController 的Java 并且把所有调用google map的function放进去.
    //ToDo: 2. 目的：处理搜索结果。在搜索结果出来之后，处理data。
        //todo 2.1 -- 根据搜索界面返回的数值创建Loc，或者直接获取Loc Class 具体传递方法未定。

    //todo 2.2 -- 在Loc 有了的情况下，根据 Loc 的坐标 设置一个marker 并显示出来。
    //MC.addmarker()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        goToSearchAct();

    }

    // Search
    private void goToSearchAct(){
        Button srch_btn = findViewById(R.id.srch_btn);
        srch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchAct = new Intent(MapsActivity.this,
                        SearchActivity.class);
                startActivity(searchAct);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        MC.Maps(mMap, currentLocation, this);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //Initialize Google Play Services
        buildGoogleApiClient();
        MC.init_map();
        MC.checkLocationPermission();

    }
    // Create a new client
    protected synchronized void buildGoogleApiClient()
    {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Request_User_Location_Code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "TAT...Permission denied", Toast.LENGTH_SHORT).show();
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
