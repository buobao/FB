package com.turman.fb.example.newservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 注册的service
 * Created by dqf on 2016/4/11.
 */

public class AService extends Service {
    public final String TAG = "AService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind function running.");
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate function running.");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand function running.");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy function running.");
        super.onDestroy();
    }
}
