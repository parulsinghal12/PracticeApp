package com.example.lenovo.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Utils {


    public static URL getImageFromUrl(){
        try {
            URL url = new URL("https://www.thelabradorsite.com/wp-content/uploads/2019/03/cute.jpg");
            return url;
        } catch (MalformedURLException e) {
            Log.d("TAG","MalformedURLException");
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        Bitmap bmImg = null;
        try {

            urlConnection.setRequestMethod("GET");
            InputStream in = urlConnection.getInputStream();

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            bmImg = BitmapFactory.decodeStream(in, null, options);

            return bmImg;
        } finally {
            urlConnection.disconnect();
        }
    }

}
