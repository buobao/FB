package com.turman.fb.example.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.turman.fb.R;
import com.turman.fb.example.toast.ToastActivity;


/**
 * Created by dqf on 2016/2/17.
 */
public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private NotificationManager mNManager;
    private Notification notify1;
    Bitmap LargeBitmap = null;
    private static final int NOTIFYID_1 = 1;

    private Button btn_show_normal;
    private Button btn_close_normal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        mContext = NotificationActivity.this;
        //创建大图标的Bitmap
        LargeBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.obama);
        mNManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        bindView();
    }

    private void bindView() {
        btn_show_normal = (Button) findViewById(R.id.btn_show);
        btn_close_normal = (Button) findViewById(R.id.btn_close);

        btn_show_normal.setOnClickListener(this);
        btn_close_normal.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                Intent intent = new Intent(mContext, ToastActivity.class);
                PendingIntent pintent = PendingIntent.getActivity(mContext, 0, intent, 0);

                //设置图片，通知标题，发送时间，提示方式等属性
                Notification.Builder mBuilder = new Notification.Builder(this);
                mBuilder.setContentTitle("抖动妹")
                        .setContentText("和我一起无尽的抖动吧！")
                        .setSubText("记住我的名字，我叫抖动妹")
                        .setTicker("收到抖动妹发来的信息")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(LargeBitmap)
                        .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                        .setSound(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.biaobiao))
                        .setAutoCancel(true)
                        .setContentIntent(pintent);
                notify1 = mBuilder.build();
                mNManager.notify(NOTIFYID_1,notify1);
                break;
            case R.id.btn_close:
                mNManager.cancel(NOTIFYID_1);   //取消notification
                break;
        }
    }
}

























