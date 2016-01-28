package com.turman.fb.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.turman.fb.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by dqf on 2016/1/20.
 */
public class ActionBar extends Activity {

   public android.app.ActionBar actionBar;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actionbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //actionBar = getActionBar();
        //actionBar.show();

        NotificationManager noti = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this)
                .setContentTitle("title").setContentText("text").setSmallIcon(R.mipmap.ic_launcher);

        Notification notification = builder.build();
        try {
            Field field = notification.getClass().getDeclaredField("extraNotification");
            Object extraNotification = field.get(notification);
            Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
            method.invoke(extraNotification, 20);
        } catch (Exception e) {
            e.printStackTrace();
        }
        noti.notify(0, notification);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //添加菜单项
        MenuItem add=menu.add(0,0,0,"add");
        MenuItem del=menu.add(0,0,0,"del");
        MenuItem save=menu.add(0,0,0,"save");
        //绑定到ActionBar
        add.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        del.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        save.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return true;
    }
}


























