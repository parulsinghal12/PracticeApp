package com.example.lenovo.myapplication.services.backend;

import com.example.lenovo.myapplication.BuildConfig;

public class Constants {
    public static final int STATUS_SUCCESSFUL = 100;
    public static final int STATUS_FAILED = 99;
    public static final String DOWNLOAD_PATH = "downloadpath";
    public static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".DWNLD_STATUS_SUCCESSFUL";

    public static final int PERM_REQUEST_CODE = 1001;
}
