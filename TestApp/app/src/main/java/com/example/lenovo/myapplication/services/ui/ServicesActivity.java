package com.example.lenovo.myapplication.services.ui;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.example.lenovo.myapplication.BuildConfig;
import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.databinding.ActivityServicesBinding;
import com.example.lenovo.myapplication.services.backend.BoundService;
import com.example.lenovo.myapplication.services.backend.Constants;
import com.example.lenovo.myapplication.services.backend.IntentServiceResultReciever;
import com.example.lenovo.myapplication.services.backend.MyIntentService;
import com.example.lenovo.myapplication.services.backend.StartService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import static com.example.lenovo.myapplication.services.backend.MyIntentService.ACTION_DWNLD;
import static com.example.lenovo.myapplication.services.backend.MyIntentService.EXTRA_PARAM1;

public class ServicesActivity extends AppCompatActivity {

    static final String TAG = "ServicesActivity";

    ActivityServicesBinding binding;
    Handler remoteServicePollingHandler = new Handler();

    //BoundService class Object
    // whenever we are able to maintain object of service it can be considered as Bound service.
    // Also we wi
    BoundService boundService;
    //boolean variable to keep a check on service bind and unbind event
    boolean isBound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_services);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG,"onResume");
        super.onResume();
        registerReceiver(mServiceBroadcastReceiver,new IntentFilter(ACTION_CUSTOM_BROADCAST));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mServiceBroadcastReceiver);
    }

    @Override
    protected void onStop() {
        Log.d(TAG,"onStop");
        super.onStop();

        //+++BOUND SERVICE +++++++++++++++++++++++//
        if(isBound){
            unbindService(boundServiceConnection);
            isBound = false;
        }
        //+++ BOUND SERVICE +++++++++++++++++++++++//
    }

    public void onStartServiceBtnClick(View view){
        // use this to start and trigger a service
        Intent startSeriveIntent= new Intent(this, StartService.class);
        // potentially add data to the intent
        startSeriveIntent.putExtra("START_SERVICE", "start service started");
        startService(startSeriveIntent);

    }

    public void onStopServiceBtnClick(View view){
        Intent startSeriveIntent= new Intent(this, StartService.class);
        stopService(startSeriveIntent);
    }

    //+++++++++++++++++++ BOUND SERVICE +++++++++++++++++++++++//

    // service binding when button is clicked
    // if service needs to be binded(for quicker action) as soon as activity is launched put it under onStart().
    public void onStartBoundServiceBtnClick(View view){
        //++bind with bound service :
        Intent intent = new Intent(this, BoundService.class);
        startService(intent);
        bindService(intent, boundServiceConnection, BIND_AUTO_CREATE);

    }

    //to automatically stop the service call it under onstop or ondestroy
    public void onStopBoundServiceBtnClick(View view){
        Log.d(TAG,"onStopBoundServiceBtnClick");

        remoteServicePollingHandler.removeCallbacks(serviceUpdateRunnable);

        if (isBound) {
            unbindService(boundServiceConnection);
            isBound = false;
        }
        Intent intent = new Intent(this,
                BoundService.class);
        stopService(intent);

        //stopService(startBoundServiceIntent);
    }

    public void onUpdateBoundServiceBtnClick(View view){
        Log.d(TAG,"onUpdateBoundServiceBtnClick");
        //update UI when service is connected
        serviceUpdateRunnable.run();
    }

    private Runnable serviceUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            // TODO : issue : unbind > update data > how chronomter still runing without any callback of service.Also boundService was set as null.

            //Log.d(TAG,"boundService.getTimestamp()");
            binding.boundServiceTv.setText(boundService.getTimestamp());
            remoteServicePollingHandler.postDelayed(serviceUpdateRunnable, 1000);
        }
    };

    ServiceConnection boundServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            Log.d(TAG,"onServiceConnected");

            BoundService.MyBinder binderBridge = (BoundService.MyBinder) iBinder ;
            //Here we r getting instance of bound service after binding in remote service
            boundService = binderBridge.getService();
            isBound = true;
            binding.updateServiceData.setEnabled(true);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG,"onServiceDisconnected");
            isBound = false;
            boundService= null;
        }
    };

    //+++++++++++++++++++ BOUND SERVICE ENDS+++++++++++++++++++++++//

    //+++++++++++++++++++ INTENT SERVICE +++++++++++++++++++++++//
    public void onstartIntentServiceBtnClick(View view){
        IntentServiceResultReciever resultReciever = new IntentServiceResultReciever(new Handler());
        resultReciever.setCallBack(new MyServiceCallback());

        Intent intent = new Intent(this, MyIntentService.class);
        intent.setAction(ACTION_DWNLD);
        intent.putExtra(EXTRA_PARAM1, resultReciever);
        startService(intent);
    }

    void showDownloadStatus(boolean isdownloaded){
        if (isdownloaded) {
            binding.downloadStatusTv.setText("Downloaded");

        } else {
            binding.downloadStatusTv.setText("Downloading....");
        }
    }

    //One way using BR other way will be result receiver
    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".DWNLD_STATUS_SUCCESSFUL";
    private BroadcastReceiver mServiceBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG,"mServiceBroadcastReceiver == ");
            if (action.equals(ACTION_CUSTOM_BROADCAST))
                Log.d(TAG,"ACTION_CUSTOM_BROADCAST downloaded");
                //showDownloadStatus(true);

        }
    };

    private class MyServiceCallback implements IntentServiceResultReciever.IntentSrvcResultReceiverCallBack {
        @Override
        public void onError(int errorCode) {
            binding.downloadStatusTv.setText("Download Failed");
        }

        @Override
        public void onSuccess(String data) {
            Log.d(TAG,"MyServiceCallback - onSuccess");
            binding.downloadStatusTv.setText("Downloaded");
            Intent i = new Intent(ServicesActivity.this,ServiceResponseActivity.class);
            i.putExtra(Constants.DOWNLOAD_PATH,data);
            startActivity(i);
        }
    }


    //+++++++++++++++++++ INTENT SERVICE +++++++++++++++++++++++//
}