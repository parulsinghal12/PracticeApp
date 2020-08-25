package com.example.lenovo.myapplication.services.backend;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.example.lenovo.myapplication.BuildConfig;

import java.util.Objects;

public class Utils {

    public static void downloadFile(Context ctx) {
        String DownloadUrl = "https://file-examples-com.github.io/uploads/2017/11/file_example_MP3_5MG.mp3";
        //https://www.mouser.in/catalog/English/103/APAC/dload/pdf/mouser.pdf
        //String DownloadUrl = "https://www.mouser.in/catalog/English/103/APAC/dload/pdf/mouser.pdf";
        DownloadManager.Request request1 = new DownloadManager.Request(Uri.parse(DownloadUrl));
        request1.setDescription("Sample Music File");   //appears the same in Notification bar while downloading
        request1.setTitle("File1.mp3");
        request1.setVisibleInDownloadsUi(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request1.allowScanningByMediaScanner();
            request1.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        }
        request1.setDestinationInExternalFilesDir(ctx, "/tempfile", "Question1.mp3");

        DownloadManager manager1 = (DownloadManager) ctx.getSystemService(Context.DOWNLOAD_SERVICE);
        Objects.requireNonNull(manager1).enqueue(request1);
        String ACTION_CUSTOM_BROADCAST =
                BuildConfig.APPLICATION_ID + ".DWNLD_STATUS_SUCCESSFUL";
        if (DownloadManager.STATUS_SUCCESSFUL == 8) {
            Toast.makeText(ctx,"download success!!!", Toast.LENGTH_SHORT).show();
            Log.d("Utils" ,"download success!!!");
            Intent intent=new Intent(ACTION_CUSTOM_BROADCAST);
            ctx.sendBroadcast(intent);
        }else if(DownloadManager.STATUS_FAILED == 16){
            Log.d("Utils" ,"download FAILED!!!");
            Toast.makeText(ctx,"download FAILED!!!", Toast.LENGTH_SHORT).show();
        }else if(DownloadManager.STATUS_PENDING == 1 || DownloadManager.STATUS_RUNNING == 2){
            Log.d("Utils" ,"download STATUS_PENDING!!STATUS_RUNNING");
        }

    }

    public static void downloadFileFromIntentService(Context ctx) {
        String DownloadUrl = "https://file-examples-com.github.io/uploads/2017/11/file_example_MP3_5MG.mp3";
        //https://www.mouser.in/catalog/English/103/APAC/dload/pdf/mouser.pdf
        //String DownloadUrl = "https://www.mouser.in/catalog/English/103/APAC/dload/pdf/mouser.pdf";
        DownloadManager.Request request1 = new DownloadManager.Request(Uri.parse(DownloadUrl));
        request1.setDescription("Sample Music File");   //appears the same in Notification bar while downloading
        request1.setTitle("File1.mp3");
        request1.setVisibleInDownloadsUi(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request1.allowScanningByMediaScanner();
            request1.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        }
        request1.setDestinationInExternalFilesDir(ctx, "/tempfile", "Question1.mp3");

        DownloadManager manager1 = (DownloadManager) ctx.getSystemService(Context.DOWNLOAD_SERVICE);
        Objects.requireNonNull(manager1).enqueue(request1);
        String ACTION_CUSTOM_BROADCAST =
                BuildConfig.APPLICATION_ID + ".DWNLD_STATUS_SUCCESSFUL";
        if (DownloadManager.STATUS_SUCCESSFUL == 8) {
            //Toast.makeText(ctx,"download success!!!", Toast.LENGTH_SHORT).show();
            Log.d("Utils" ,"download success!!!");
            Intent intent=new Intent(ACTION_CUSTOM_BROADCAST);
            ctx.sendBroadcast(intent);
        }else if(DownloadManager.STATUS_FAILED == 16){
            Log.d("Utils" ,"download FAILED!!!");
            //Toast.makeText(ctx,"download FAILED!!!", Toast.LENGTH_SHORT).show();
        }else if(DownloadManager.STATUS_PENDING == 1 || DownloadManager.STATUS_RUNNING == 2){
            Log.d("Utils" ,"download STATUS_PENDING!!STATUS_RUNNING");
        }

    }
}
