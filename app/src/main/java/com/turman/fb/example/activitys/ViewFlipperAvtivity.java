package com.turman.fb.example.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ViewFlipper;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/2/16.
 */
public class ViewFlipperAvtivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewflipper);
        ViewFlipper vf = (ViewFlipper) findViewById(R.id.vif);
        vf.startFlipping();
    }
}
