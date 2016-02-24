package com.turman.fb.example.service.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/2/23.
 */
public class ServiceDemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicedemo);

        final Button startService = (Button) findViewById(R.id.startService);
        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent("com.turman.fb.example.service.demo.SEND_SERVICE"));
            }
        });
    }
}
