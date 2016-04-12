package com.turman.fb.example.newservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.turman.fb.R;
import com.turman.fb.example.http.HtmlActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dqf on 2016/4/11.
 */
public class ServiceTestActivity extends Activity {
    private final String TAG = "ServiceTestActivity";

    @Bind(R.id.start_btn)
    protected Button mStart;
    @Bind(R.id.end_btn)
    protected Button mEnd;
    @Bind(R.id.start_client)
    protected Button mClient;
    @Bind(R.id.start_app)
    protected Button mApp;
    @Bind(R.id.normal)
    protected Button mNormal;

    @Bind(R.id.bind_service)
    protected Button mBind;
    @Bind(R.id.unbind_service)
    protected Button mUnbind;
    @Bind(R.id.get_count)
    protected Button mGetCount;

    private BService.MyBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate function running.");
        setContentView(R.layout.activity_service_test);
        ButterKnife.bind(this);

        Intent i = new Intent(ServiceTestActivity.this, NetworkService.class);
        startService(i);

        Intent i1 = new Intent(ServiceTestActivity.this, LongRunningService.class);
        startService(i1);


        final Intent intent = new Intent();

        intent.setAction("com.turman.fb.example.newservice.AService");
        intent.setPackage("com.turman.fb");
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });

        mEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });

        mClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动其他应用的activity
                Intent sub_intent = new Intent();
                sub_intent.setAction("com.turman.app.client.Query");
                sub_intent.setPackage("com.turman.app.client");
//                sub_intent.setClassName("com.turman.app.client", "com.turman.app.client.ClientActivity");
//                sub_intent.setComponent(new ComponentName("com.turman.app.client","com.turman.app.client.ClientActivity"));
                startActivity(sub_intent);

            }
        });

        mApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent();
                intent.setAction("com.turman.fb.example.drable.ABC");
                intent.setPackage("com.turman.fb");
                startActivity(intent);
            }
        });

        mNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceTestActivity.this, HtmlActivity.class);
                startActivity(intent);
            }
        });


        final ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i(TAG, "onServiceConnected function funning.");
                Log.i(TAG, "ComponentName.mPackage:"+name.getPackageName());
                Log.i(TAG, "ComponentName.mClass:"+name.getClassName());
                binder = (BService.MyBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i(TAG, "onServiceDisconnected function funning.");
            }
        };

        final Intent intent1 = new Intent();
        intent1.setAction("com.turman.fb.example.newservice.BService");

        mBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent1, connection, Context.BIND_AUTO_CREATE);
                Toast.makeText(ServiceTestActivity.this, "Bind service finished.", Toast.LENGTH_SHORT).show();
            }
        });

        mUnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
                Toast.makeText(ServiceTestActivity.this, "Unbind service finished.", Toast.LENGTH_SHORT).show();
            }
        });

        mGetCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ServiceTestActivity.this,"Current count is:"+binder.getCount(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}




















