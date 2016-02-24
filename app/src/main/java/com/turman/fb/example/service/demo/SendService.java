package com.turman.fb.example.service.demo;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.turman.fb.R;
import com.turman.fb.example.IndexActivity;

import java.util.Date;

/**
 * Created by dqf on 2016/2/23.
 */
public class SendService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Notification.Builder localBuilder = new Notification.Builder(this);

        localBuilder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, IndexActivity.class), 0));
        localBuilder.setAutoCancel(false);
        localBuilder.setSmallIcon(R.mipmap.ic_launcher);
        localBuilder.setTicker("Foreground Service Start");
        localBuilder.setContentText("Socket服务端");
        localBuilder.setContentText("正在运行...");
        startForeground(1, localBuilder.getNotification());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("BackService", new Date().toString());
            }
        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 2 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this,AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
