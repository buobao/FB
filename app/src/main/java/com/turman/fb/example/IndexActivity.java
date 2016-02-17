package com.turman.fb.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.turman.fb.R;
import com.turman.fb.example.activitys.ViewFlipperActivity2;
import com.turman.fb.example.activitys.ViewFlipperAvtivity;

/**
 * Created by dqf on 2016/2/16.
 */
public class IndexActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_activity);

        TextView tx1 = (TextView) findViewById(R.id.ViewFlipper);
        tx1.setOnClickListener(this);
        TextView tx2 = (TextView) findViewById(R.id.ViewFlipper2);
        tx2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ViewFlipper:
                Intent intent = new Intent(IndexActivity.this, ViewFlipperAvtivity.class);
                startActivity(intent);
                break;
            case R.id.ViewFlipper2:
                Intent intent1 = new Intent(IndexActivity.this, ViewFlipperActivity2.class);
                startActivity(intent1);
                break;
        }
    }
}























