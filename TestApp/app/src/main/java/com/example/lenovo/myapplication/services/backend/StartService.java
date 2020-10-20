package com.example.lenovo.myapplication.services.backend;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.IBinder;
import android.util.Log;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.services.ui.ServicesActivity;

import java.io.FileDescriptor;
import java.io.PrintWriter;

import androidx.core.app.NotificationCompat;

public class StartService extends Service {

    NotificationManager notificationManager;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO do something useful
        Log.d("StartService" ,"onStartCommand started");
        showDownloadingNotification();

        for(int i = 0;i < 10; i++){
            try {
                Log.d("StartService","i=="+i);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Utils.downloadFile(getApplicationContext());
        //stopSelf(); -> currenlty called from activity(which sometimes crashes)
        Log.d("StartService" ,"onStartCommand ends");
        return StartService.START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("StartService" ,"onDestroy");
        super.onDestroy();
        //notificationManager.cancel(0);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(fd, writer, args);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    public IBinder onBind(Intent intent) {
        //TODO for communication return IBinder implementation
        return null;
    }

    private void showDownloadingNotification() {
        String text = getText(R.string.downloading).toString();

        // The PendingIntent to launch our activity if the user selects this notification
        Intent intent = new Intent(this, ServicesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        final PendingIntent pendingIntent  = PendingIntent.getActivity(
                this, 0,intent, PendingIntent.FLAG_UPDATE_CURRENT);


        notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createChannel(notificationManager);
        String CHANNEL_ID = "my_channel_01";
        // Set the info for the views that show in the notification panel.
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.start_service)  // the status icon
                .setTicker(text)  // the status text
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle(getText(R.string.downloading))  // the label of the entry
                .setContentText(text)  // the contents of the entry
                .setContentIntent(pendingIntent ) ; // The intent to send when the entry is clicked

        // Send the notification.
        notificationManager.notify(1,notificationBuilder.build());
    }
    //https://www.aanandshekharroy.com/articles/2019-01/bound-services-in-android

    private void createChannel(NotificationManager notificationManager) {
        String id = "my_channel_01";
        String description = "Notifications for download status";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        NotificationChannel mChannel = new NotificationChannel(id, description, importance);
        mChannel.setDescription(description);
        mChannel.enableLights(true);
        mChannel.setLightColor(Color.BLUE);
        notificationManager.createNotificationChannel(mChannel);
    }
}
