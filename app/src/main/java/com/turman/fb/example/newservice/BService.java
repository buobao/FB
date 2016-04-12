package com.turman.fb.example.newservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by dqf on 2016/4/11.
 */
public class BService extends Service {
    private final String TAG = "BService";
    private boolean isUnbind = false;
    private int count = 0;
    private MyBinder binder = new MyBinder();
    public class MyBinder extends Binder {
        public int getCount(){
            return BService.this.count;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate function running.");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isUnbind) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        }).start();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand function running.");
        //return super.onStartCommand(intent, flags, startId);
        return START_REDELIVER_INTENT;   //如果希望app退出service还能运行，需要在这里返回该值
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy function running.");
        isUnbind = true;
        super.onDestroy();
    }
}
