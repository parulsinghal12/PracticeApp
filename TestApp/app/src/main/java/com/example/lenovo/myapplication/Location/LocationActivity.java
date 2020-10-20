package com.example.lenovo.myapplication.Location;

import android.os.Bundle;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.databinding.ActivityLocationBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class LocationActivity extends AppCompatActivity {

    ActivityLocationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_location);
        //requestAppPermissions();

        Workable workable = new Workable<GPSPoint>() {
            @Override
            public void work(GPSPoint gpsPoint) {
                binding.locationText.setText(gpsPoint.toString());
                // draw something in the UI with this new data
            }
        };

        MyLocationService.instance(this).onChange(workable);
    }
}