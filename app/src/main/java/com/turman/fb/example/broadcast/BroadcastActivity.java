package com.turman.fb.example.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/2/23.
 */
public class BroadcastActivity extends AppCompatActivity {

    private MyBRReceiver myBRReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        myBRReceiver = new MyBRReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(myBRReceiver, intentFilter);

        TextView t = (TextView) findViewById(R.id.send_msg);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(new Intent("com.turman.fb.example.broadcast.DefinedReceiver"));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBRReceiver);
    }
}
