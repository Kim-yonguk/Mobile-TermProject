package com.example.a1a1a1.lifelogger;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Created by 1a1a1 on 2016-11-26.
 */
public class GetGps extends Service implements LocationListener {

    private Context mContext;
    double latitude = 0;
    double longitude = 0;

    private static final long UPDATE_TIME = 1;
    private static final long UPDATE_DISTANCE = 10;
    boolean isGPSEnable = false;
    boolean isNetworkEnable = false;

    public GetGps(Context context) {
        this.mContext = context;
    }

    Location location;
    protected LocationManager locationManager;


    public Location getLocation(){

        try {
            locationManager = (LocationManager)mContext.getSystemService(LOCATION_SERVICE);
            isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if(isNetworkEnable){
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,UPDATE_TIME,UPDATE_DISTANCE, this);
                if(locationManager != null){
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if(location != null){
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                    }
                }
            }
            if(isGPSEnable){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,UPDATE_TIME,UPDATE_DISTANCE, this);
                if(locationManager != null){
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if(location != null){
                        latitude = getLatitude();
                        longitude = getLongitude();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return location;
    }



    //위도
    public double getLatitude(){
        return latitude;
    }

    //경도
    public double getLongitude(){
        return longitude;
    }


    public IBinder onBind(Intent arg0) {return null;}

    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }
}
