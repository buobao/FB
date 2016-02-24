package com.turman.fb.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by dqf on 2016/2/23.
 */
public class MyBRReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //这里写需要处理的内容
        Toast.makeText(context,"网络状况发生变化",Toast.LENGTH_SHORT).show();
    }
}
