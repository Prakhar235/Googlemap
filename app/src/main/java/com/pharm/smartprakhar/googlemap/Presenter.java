package com.pharm.smartprakhar.googlemap;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.LOCATION_SERVICE;
@Module
public class Presenter {

  private  View view;

  @Inject
     Presenter() {

    }

    public void setView(View view)
    {
        this. view=view;

    }

    public void setlocation(LocationManager locationManager, final Context context)
    {




        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


           ActivityCompat.requestPermissions((Activity) context,new String[] { Manifest.permission. ACCESS_FINE_LOCATION},
                    3);
            ActivityCompat.requestPermissions((Activity) context,new String[] { Manifest.permission. ACCESS_COARSE_LOCATION},
                    4);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
        {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude=location.getLatitude();
                    double longitude=location.getLongitude();



                    LatLng latLng=new LatLng(latitude,longitude);
                    Geocoder geocoder=new Geocoder(context);
                    try {
                        List<Address> addressList =   geocoder.getFromLocation(latitude,longitude,1);
                        String str=addressList.get(0).getLocality()+",";
                        str+=addressList.get(0).getCountryName();
                        view.displaylocation(latLng,str);
                        //  mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        //   mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                    } catch (IOException e) {


                    }

                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });


        }
        else
        {
            if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        double latitude=location.getLatitude();
                        double longitude=location.getLongitude();

                        LatLng latLng=new LatLng(latitude,longitude);
                        Geocoder geocoder=new Geocoder(context);
                        try {
                            List<Address>  addressList =   geocoder.getFromLocation(latitude,longitude,1);
                            String str=addressList.get(0).getLocality()+",";
                            str+=addressList.get(0).getCountryName();
                           view.displaylocation(latLng,str);
                        } catch (IOException e) {


                        }


                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                });

            }

        }



    }


}
