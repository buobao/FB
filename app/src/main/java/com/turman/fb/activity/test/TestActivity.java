package com.turman.fb.activity.test;

import android.app.Activity;
import android.os.Bundle;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/1/27.
 */
public class TestActivity extends Activity {
    private Container container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        container = (Container) findViewById(R.id.container);
    }

    public Container getContainer() {
        return container;
    }

    @Override
    public void onBackPressed() {
        boolean handled = container.onBackPressed();
        if (!handled) {
            finish();
        }
    }
}