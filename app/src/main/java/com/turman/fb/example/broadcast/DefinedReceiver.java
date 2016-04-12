package com.turman.fb.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/4/11.
 */
public class DefinedReceiver extends BroadcastReceiver {
    private final String ACTION_BOOT = "com.turman.fb.example.broadcast.DefinedReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
//        if (ACTION_BOOT.equals(intent.getAction())) {
            Toast.makeText(context, "收到广播，请指示！", Toast.LENGTH_SHORT).show();
//        }
    }
}
