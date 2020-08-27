package com.example.lenovo.myapplication.services.backend;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Intent;
import android.os.ResultReceiver;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 *
 * helper methods.
 */
public class MyIntentService extends IntentService {

    static final String TAG = "MyIntentService";

    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_DWNLD = "com.example.lenovo.myapplication.services.backend.action.DOWNLOAD";
    private static final String ACTION_BAZ = "com.example.lenovo.myapplication.services.backend.action.BAZ";

    public static final String EXTRA_PARAM1 = "com.example.lenovo.myapplication.services.backend.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.example.lenovo.myapplication.services.backend.extra.PARAM2";

    public MyIntentService() {
        super("MyIntentService");
        Log.d(TAG,"MyIntentService");
    }

    @Override
    public ComponentName startForegroundService(Intent service) {
        return super.startForegroundService(service);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate");
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG,"onRebind");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG,"onHandleIntent");
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_DWNLD.equals(action)) {
                final ResultReceiver resultRecvr = intent.getParcelableExtra(EXTRA_PARAM1);
                handleActionFoo(resultRecvr);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(ResultReceiver param1) {
        //main logic:
        Log.d(TAG,"handleActionFoo");

        for(int i = 0;i < 10; i++){
            try {
                Log.d("StartService","i=="+i);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Utils.downloadFileFromIntentService(getApplicationContext(),param1);
    }

  }
