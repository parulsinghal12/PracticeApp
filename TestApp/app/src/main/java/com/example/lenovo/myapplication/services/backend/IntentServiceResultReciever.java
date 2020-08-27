package com.example.lenovo.myapplication.services.backend;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class IntentServiceResultReciever extends ResultReceiver {

    IntentSrvcResultReceiverCallBack callback;

    public IntentServiceResultReciever(Handler handler) {
        super(handler);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if(callback !=null){
            if(resultCode == Constants.STATUS_SUCCESSFUL){
                String path = resultData.getString(Constants.DOWNLOAD_PATH);
                callback.onSuccess(path);
            }else{
                callback.onError(resultCode);
            }
        }
    }

    public void setCallBack(IntentSrvcResultReceiverCallBack intentSrvcResultRecieverCallBack){
        callback = intentSrvcResultRecieverCallBack;
    }

    public interface IntentSrvcResultReceiverCallBack{
        void onError(int errorCode);
        void onSuccess(String data);
    }
}
