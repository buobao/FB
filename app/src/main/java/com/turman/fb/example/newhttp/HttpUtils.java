package com.turman.fb.example.newhttp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.MainThread;
import android.view.View;
import android.widget.ImageView;

import com.turman.fb.example.http.StreamTool;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Administrator on 2016/4/9.
 */
public class HttpUtils {

    private static HttpUtils instance;
    private static Context mContext;

    private HttpUtils(){}

    public static HttpUtils getInstance(Context context){
        if (instance == null) {
            instance = new HttpUtils();
        }
        mContext = context;
        return instance;
    }

    public void loadImage(String path, final ImageView imageView) {
        URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200){
                throw  new RuntimeException("请求url失败");
            }
            InputStream inputStream = conn.getInputStream();
            byte[] bt = StreamTool.read(inputStream);
            inputStream.close();

            final Bitmap bitmap = BitmapFactory.decodeByteArray(bt,0,bt.length);
            ((Activity)mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                    imageView.setVisibility(View.VISIBLE);
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDataFromUrl(String str) {
        try {
            URL url = new URL(str);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == 200){
                InputStream in = connection.getInputStream();
                byte[] data = StreamTool.read(in);
                String html = new String(data,"UTF-8");
                return html;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
