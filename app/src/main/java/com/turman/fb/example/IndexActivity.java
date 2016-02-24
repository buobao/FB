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
import com.turman.fb.example.alertdialog.AlertDialogActivity;
import com.turman.fb.example.alertdialog.AlertDialogActivity2;
import com.turman.fb.example.alertdialog.AlertDialogActivity3;
import com.turman.fb.example.broadcast.BroadcastActivity;
import com.turman.fb.example.broadcast.demo.LoginActivity;
import com.turman.fb.example.notification.NotificationActivity;
import com.turman.fb.example.popupwindow.PopUpWindowActivity;
import com.turman.fb.example.service.TestServiceActivity;
import com.turman.fb.example.service.demo.ServiceDemoActivity;
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
        TextView tx4 = (TextView) findViewById(R.id.Notification);
        tx4.setOnClickListener(this);
        TextView tx5 = (TextView) findViewById(R.id.AlertDialog);
        tx5.setOnClickListener(this);
        TextView tx6 = (TextView) findViewById(R.id.AlertDialog2);
        tx6.setOnClickListener(this);
        TextView tx7 = (TextView) findViewById(R.id.AlertDialog3);
        tx7.setOnClickListener(this);
        TextView tx8 = (TextView) findViewById(R.id.PopUpWindow);
        tx8.setOnClickListener(this);
        TextView tx9 = (TextView) findViewById(R.id.Service1);
        tx9.setOnClickListener(this);
        TextView tx10 = (TextView) findViewById(R.id.ServiceDemo);
        tx10.setOnClickListener(this);
        TextView tx11 = (TextView) findViewById(R.id.Broadcast);
        tx11.setOnClickListener(this);
        TextView tx12 = (TextView) findViewById(R.id.BroadcastDemo);
        tx12.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ViewFlipper:
                //Intent intent = new Intent(IndexActivity.this, ViewFlipperAvtivity.class);
                intent.setComponent(new ComponentName(IndexActivity.this, ViewFlipperAvtivity.class));
                break;
            case R.id.ViewFlipper2:
//                Intent intent1 = new Intent(IndexActivity.this, ViewFlipperActivity2.class);
                intent.setComponent(new ComponentName(IndexActivity.this, ViewFlipperActivity2.class));

                break;
            case  R.id.Toast1:
                intent.setComponent(new ComponentName(IndexActivity.this, ToastActivity.class));
                break;
            case R.id.Notification:
                intent.setComponent(new ComponentName(IndexActivity.this, NotificationActivity.class));
                break;
            case R.id.AlertDialog:
                intent.setComponent(new ComponentName(IndexActivity.this, AlertDialogActivity.class));
                break;
            case R.id.AlertDialog2:
                intent.setComponent(new ComponentName(IndexActivity.this, AlertDialogActivity2.class));
                break;
            case R.id.AlertDialog3:
                intent.setComponent(new ComponentName(IndexActivity.this, AlertDialogActivity3.class));
                break;
            case R.id.PopUpWindow:
                intent.setComponent(new ComponentName(IndexActivity.this, PopUpWindowActivity.class));
                break;
            case R.id.Service1:
                intent.setComponent(new ComponentName(IndexActivity.this, TestServiceActivity.class));
                break;
            case R.id.ServiceDemo:
                intent.setComponent(new ComponentName(IndexActivity.this, ServiceDemoActivity.class));
                break;
            case R.id.Broadcast:
                intent.setComponent(new ComponentName(IndexActivity.this, BroadcastActivity.class));
                break;
            case R.id.BroadcastDemo:
                intent.setComponent(new ComponentName(IndexActivity.this, LoginActivity.class));
                break;
        }
        startActivity(intent);
    }
}























