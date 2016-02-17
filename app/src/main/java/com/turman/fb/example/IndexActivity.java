package com.turman.fb.example;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.turman.fb.R;
import com.turman.fb.example.activitys.ViewFlipperActivity2;
import com.turman.fb.example.activitys.ViewFlipperAvtivity;
import com.turman.fb.example.toast.ToastActivity;

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
        TextView tx3 = (TextView) findViewById(R.id.Toast1);
        tx3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ViewFlipper:
                //Intent intent = new Intent(IndexActivity.this, ViewFlipperAvtivity.class);
                intent.setComponent(new ComponentName(IndexActivity.this, ViewFlipperAvtivity.class));
                startActivity(intent);
                break;
            case R.id.ViewFlipper2:
//                Intent intent1 = new Intent(IndexActivity.this, ViewFlipperActivity2.class);
                intent.setComponent(new ComponentName(IndexActivity.this, ViewFlipperActivity2.class));
                startActivity(intent);

                break;
            case  R.id.Toast1:
                intent.setComponent(new ComponentName(IndexActivity.this, ToastActivity.class));
                startActivity(intent);
                break;
        }
    }
}























