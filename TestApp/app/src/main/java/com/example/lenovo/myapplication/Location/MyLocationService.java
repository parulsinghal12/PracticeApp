package com.example.lenovo.myapplication.Location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;

//REFERENCE : https://stackoverflow.com/questions/28535703/best-way-to-get-user-gps-location-in-background-in-android

public class MyLocationService {
    private static final MyLocationService instance = new MyLocationService();

    private static final String TAG = MyLocationService.class.getSimpleName();
    static Context ctx ;



    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;
    private LocationSettingsRequest locationSettingsRequest;

    private Workable<GPSPoint> workable;

    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 1000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 1000;

    @SuppressLint("MissingPermission")
    private MyLocationService() {
        this.locationRequest = new LocationRequest();
        this.locationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        this.locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        this.locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(this.locationRequest);
        this.locationSettingsRequest = builder.build();

        this.locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult); // why? this. is. retarded. Android.
                Location currentLocation = locationResult.getLastLocation();

                GPSPoint gpsPoint = new GPSPoint(currentLocation.getLatitude(), currentLocation.getLongitude());
                Log.i(TAG, "Location Callback results: " + gpsPoint);//comment
                if (null != workable)
                    workable.work(gpsPoint);
            }
        };

        this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(ctx.getApplicationContext());
        this.mFusedLocationClient.requestLocationUpdates(this.locationRequest,
                this.locationCallback, Looper.myLooper());
    }

    public static MyLocationService instance(Context context) {
        ctx = context;
        return instance;
    }

    public void onChange(Workable<GPSPoint> workable) {
        this.workable = workable;
    }

    public LocationSettingsRequest getLocationSettingsRequest() {
        return this.locationSettingsRequest;
    }

    public void stop() {
        Log.i(TAG, "stop() Stopping location tracking");
        this.mFusedLocationClient.removeLocationUpdates(this.locationCallback);
    }

}
