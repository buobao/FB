package com.turman.fb.example.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by dqf on 2016/2/23.
 */
public class TestService3 extends IntentService {
    private final String TAG = "testserver3";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public TestService3() {
        super("TestServer3");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getExtras().getString("param");
        if (action.equals("s1")) Log.i(TAG,"启动server1");
        else if (action.equals("s2")) Log.i(TAG, "启动server2");
        else if (action.equals("s3")) Log.i(TAG, "启动server3");

        //休眠
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind");
        return super.onBind(intent);
    }

    @Override
    public void onCreate() {
        Log.i(TAG,"onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void setIntentRedelivery(boolean enabled) {
        super.setIntentRedelivery(enabled);
        Log.i(TAG, "setIntentRedelivery");
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"onDestroy");
        super.onDestroy();
    }
}
