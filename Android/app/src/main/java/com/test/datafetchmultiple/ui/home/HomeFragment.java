package com.test.datafetchmultiple.ui.home;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.test.datafetchmultiple.Main2Activity;
import com.test.datafetchmultiple.NavActivity;
import com.test.datafetchmultiple.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements OnMapReadyCallback{

    public HomeFragment(){

    };
    private GoogleMap mMap;
    ArrayList<LatLng>arrayList=new ArrayList<LatLng>();
    LatLng sydney = new LatLng(20.326851,85.801649);
    LatLng home = new LatLng(20.325040,85.802786);
    LatLng house = new LatLng(20.327042,85.802711);
    Button button;
    FusedLocationProviderClient fusedLocationProviderClient;
    Location currentLocation;
    private static final int REQUEST_CODE = 101;
    final private int userAgreePermissionCode = 1;
    double lati ;
    double longi ;
    double lt ;
    double ln ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int currentPermission = ActivityCompat.checkSelfPermission(getActivity(),android.Manifest.permission.ACCESS_FINE_LOCATION);
        if( currentPermission!=PackageManager.PERMISSION_GRANTED ) ActivityCompat.requestPermissions(getActivity(),
                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, userAgreePermissionCode);

        FusedLocationProviderClient userLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());  //  Error in here!!

        Task<Location> location =  userLocationProviderClient.getLastLocation();
        location.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                currentLocation = location;
                Log.i("ongitude:", currentLocation.getLongitude() + "");
                Log.i("atitude:", currentLocation.getLatitude() + "");
                lati = currentLocation.getLongitude();
                longi = currentLocation.getLatitude();
                lt = lati;
                ln = longi;
                Log.i("LongitudeonC:",lt + "");
                Log.i("LatitudeonC:", ln + "");
                Log.i("LongitudeonCre:",currentLocation.getLatitude() + "");
                Log.i("LatitudeonCre:", currentLocation.getLatitude() + "");

                SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map1);
                assert supportMapFragment != null;
                supportMapFragment.getMapAsync(HomeFragment.this);
                arrayList.add(sydney);
                arrayList.add(home);
                arrayList.add(house);
            }
        });


        

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        Button button = (Button) v.findViewById(R.id.imageView123);
        NavActivity activity = (NavActivity) getActivity();
        final String myDataFromActivity = activity.getMyData();
        final String myEmailFromActivity = activity.getMyEmail();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail(myDataFromActivity,myEmailFromActivity);
            }
        });

        return v;
    }

    public void updateDetail(String myDataFromActivity, String myEmailFromActivity) {
        Intent intent = new Intent(getActivity(), Main2Activity.class);
        intent.putExtra("user_name", myDataFromActivity);
        intent.putExtra("email_add", myEmailFromActivity);
        intent.putExtra("lati", longi);
        intent.putExtra("long", lati);
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
//        for(int i=0; i<arrayList.size();i++){
//            mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title("Marker"));
//            mMap.animateCamera(CameraUpdateFactory.zoomTo(16.0f));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
//        }
        LatLng latLng = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
//        BitmapDescriptor bd = BitmapDescriptorFactory.HUE_GREEN
        MarkerOptions markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).position(latLng).title("Your current location!");
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        mMap.addMarker(markerOptions);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case userAgreePermissionCode:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    Log.i("Status:","Granted");
                } else {
                    // Permission Denied
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }


}
