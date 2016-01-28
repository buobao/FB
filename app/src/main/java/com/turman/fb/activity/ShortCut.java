package com.turman.fb.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/1/20.
 */
public class ShortCut extends Activity {

    public static final String ACTION_ADD_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    public static final String ACTION_REMOVE_SHORTCUT = "com.android.launcher.action.UNINSTALL_SHORTCUT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent actionIntent = new Intent();
        actionIntent.setAction("com.turman.fb.activity.MainActivity");
        addShortCut(this,actionIntent,"defined",false, BitmapFactory.decodeResource(getResources(),R.drawable.obama));
    }

    public static void addShortCut(Context context, Intent actionIntent, String name, boolean allowRepeat, Bitmap icon) {
        Intent addShortCutIntent = new Intent(ACTION_ADD_SHORTCUT);

        addShortCutIntent.putExtra("duplicate",allowRepeat);
        addShortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME,name);
        addShortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON,icon);
        addShortCutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT,actionIntent);
        context.sendBroadcast(addShortCutIntent);
    }

    public static void removeShortcut(Context context, Intent actionIntent, String name) {
        Intent intent = new Intent(ACTION_REMOVE_SHORTCUT);
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
//        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.putExtra("duplicate", false);
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, actionIntent);
        context.sendBroadcast(intent);
    }
}















