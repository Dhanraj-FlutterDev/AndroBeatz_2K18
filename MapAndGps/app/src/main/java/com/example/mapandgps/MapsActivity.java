package com.example.mapandgps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
      SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

  //  SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
    //        .findFragmentById(R.id.map);
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions
                    (this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},101);
            return;
        }
        mMap.setMyLocationEnabled(true);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    public void onSearch(View view)
    {
        EditText location =(EditText)findViewById(R.id.location);
        String loc=location.getText().toString();
        List<Address>adapter=null;
        if(!(loc.equals("")))
        {
            Geocoder geocoder=new Geocoder(this);
            try
            {
                adapter=geocoder.getFromLocationName(loc,1);

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            Address address=adapter.get(0);
            LatLng latLng=new LatLng(address.getLatitude(),address.getLongitude());
            CameraPosition position =new CameraPosition.Builder()
                    .target(latLng).zoom(30).tilt(20).build();
            mMap.addMarker(new MarkerOptions().position(latLng).title(loc));
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(position));
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            Toast.makeText(getApplicationContext(),loc+latLng,Toast.LENGTH_LONG).show();
        }
    }
}
