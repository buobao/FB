package com.turman.fb.example.service;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/2/22.
 */
public class TestServiceActivity extends Activity {

    private Button start;
    private Button stop;

    private Button bind;
    private Button unbind;
    private Button status;

    TestService2.MyBinder binder;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("------Service Connected-------");
            binder = (TestService2.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("---------Service Disconnected---------");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testservice);

        start = (Button) findViewById(R.id.btn_start);
        stop = (Button) findViewById(R.id.btn_stop);
        bind = (Button) findViewById(R.id.btn_bind);
        unbind = (Button) findViewById(R.id.unbind);
        status = (Button) findViewById(R.id.status);

        //第一个实例
        final Intent intent = new Intent();

        intent.setAction("com.turman.fb.example.service.TEST_SERVICE1");

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });


        //第二个实例
        final Intent intent1 = new Intent();
        intent.setAction("com.turman.fb.example.service.TEST_SERVICE2");
        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent, conn, Service.BIND_AUTO_CREATE);
            }
        });

        unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);
            }
        });

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Service的count值为:"+binder.getCount(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}


































