package com.example.team3;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final float zoom = 18.51f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        //center map and set zoom level
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom
                (new LatLng(42.388763,-71.2201342), zoom));


        //Bentley Bath Bombs Location and Marker
        LatLng bentley = new LatLng(42.388763,-71.2201342);
        mMap.addMarker(new MarkerOptions()
                .position(bentley)
                .title("Bentley Bath Bombs")
                .snippet("Have fun with bath bombs and more!")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
    }


}
