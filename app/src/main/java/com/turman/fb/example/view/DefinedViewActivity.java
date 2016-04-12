package com.turman.fb.example.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/4/12.
 */
public class DefinedViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defined_view);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_defined_sub_view,new LinearLayout(this),true);
        SimpleLayout l = (SimpleLayout) findViewById(R.id.parent_layout);
        l.addView(v);
    }
}
