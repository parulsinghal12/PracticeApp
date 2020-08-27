package com.example.lenovo.myapplication.services.ui;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.MediaController;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.databinding.ActivityServiceResponseBinding;
import com.example.lenovo.myapplication.services.backend.Constants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.ContentValues.TAG;
import static com.example.lenovo.myapplication.services.backend.Constants.PERM_REQUEST_CODE;

public class ServiceResponseActivity extends AppCompatActivity {

    ActivityServiceResponseBinding binding;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_service_response);

        Bundle b = getIntent().getExtras();
        path = b.getString(Constants.DOWNLOAD_PATH);

        requestAppPermissions();



    }

    private void requestAppPermissions() {

        if (checkSelfPermissions()) {
            // You can use the API that requires the permission.
            performAction();
        } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected. In this UI,
            // include a "cancel" or "no thanks" button that allows the user to
            // continue using your app without granting the permission.
            //showInContextUI();
            new AlertDialog.Builder(this).setMessage("You need to allow access the permissions")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(new String[]{READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE},
                                        PERM_REQUEST_CODE);
                            }
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .create()
                    .show();
            return;
        } else {
            // You can directly ask for the permission.
            ActivityCompat.requestPermissions(this,
                    new String[] { READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE },
                    PERM_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERM_REQUEST_CODE:
                if(grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    performAction();
                }else{
                    finish();
                }
                break;
        }
    }

    private void performAction() {


        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(binding.videoHolder);

        Log.d(TAG,"path="+path);
        //Uri uri = Uri.parse(path);

        Uri uri=Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+"/File1.mp4");

        binding.videoHolder.setMediaController(mediaController);
        binding.videoHolder.setVideoURI(uri);
        binding.videoHolder.setZOrderOnTop(true);
        binding.videoHolder.start();
        binding.videoHolder.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(1.0f, 1.0f);
            }
        });
    }

    private boolean checkSelfPermissions(){
        int resultread = ContextCompat.checkSelfPermission(this,READ_EXTERNAL_STORAGE);
        int resultwrite = ContextCompat.checkSelfPermission(this,WRITE_EXTERNAL_STORAGE);

        return resultread == PackageManager.PERMISSION_GRANTED && resultwrite == PackageManager.PERMISSION_GRANTED;
    }
}