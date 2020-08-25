package com.example.lenovo.myapplication.services.backend;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;

public class BoundService extends Service {

    static final String TAG = "BoundService";

    //Instance of inner class created to provide access  to public methods in this class
    private final IBinder localBinder = new MyBinder();

    private Chronometer mChronometer;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO do something useful
        Log.d(TAG ,"onStartCommand started");
        /*for(int i = 0;i < 10; i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        //Utils.downloadFile(getApplicationContext());
        //Log.d(TAG ,"onStartCommand ends");
        return BoundService.START_REDELIVER_INTENT;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate");
        mChronometer = new Chronometer(this);
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d(TAG,"onStart");
        super.onStart(intent, startId);
    }


    /**
     This method is  Called when activity have disconnected from a particular interface published by the service.
     Note: Default implementation of the  method just  return false */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"onUnbind");
        //return super.onUnbind(intent);
        return true;
    }

    /**
     * Called when an activity is connected to the service, after it had
     * previously been notified that all had disconnected in its
     * onUnbind method.  This will only be called by system if the implementation of onUnbind method was overridden to return true.
     */
    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG,"onRebind");
        super.onRebind(intent);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind");
        //TODO for communication return IBinder implementation
        return localBinder;
        //return null;
    }

    /**
     * Called by the system to notify a Service that it is no longer     used and is being removed.  The
     * service should clean up any resources it holds (threads,       registered
     * receivers, etc) at this point.  Upon return, there will be no more calls
     * in to this Service object and it is effectively dead.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG ,"onDestroy");
        mChronometer.stop();
    }

    public class MyBinder extends Binder {

        public BoundService getService() {
            return BoundService.this;

        }
    }

    //main logic:
    public String getTimestamp() {
        long elapsedMillis = SystemClock.elapsedRealtime()
                - mChronometer.getBase();
        int hours = (int) (elapsedMillis / 3600000);
        int minutes = (int) (elapsedMillis - hours * 3600000) / 60000;
        int seconds = (int) (elapsedMillis - hours * 3600000 - minutes * 60000) / 1000;
        int millis = (int) (elapsedMillis - hours * 3600000 - minutes * 60000 - seconds * 1000);
        return hours + ":" + minutes + ":" + seconds + ":" + millis;
    }
}
