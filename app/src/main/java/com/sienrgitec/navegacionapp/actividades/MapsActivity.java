package com.sienrgitec.navegacionapp.actividades;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sienrgitec.navegacionapp.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double vlon = 0.0 , vlat = 0.0;
    String vcRazonS = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        vlat = getIntent().getExtras().getDouble("deLat");
        vlon = getIntent().getExtras().getDouble("deLon");
        vcRazonS = getIntent().getExtras().getString("vcNegocio");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng vcNegocio = new LatLng(vlat, vlon);
        mMap.addMarker(new MarkerOptions().position(vcNegocio).title(vcRazonS));

        CameraPosition camera = new CameraPosition.Builder().target(vcNegocio)
                .zoom(15)
                .bearing(90)
                .tilt(30)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
    }
}