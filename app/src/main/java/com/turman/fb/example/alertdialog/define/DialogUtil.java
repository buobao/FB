package com.turman.fb.example.alertdialog.define;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/2/17.
 */
public class DialogUtil{

    private Context mContext;
    private AlertDialog.Builder builder = null;
    private static AlertDialog defineDialog = null;

    public static AlertDialog getInstance(Context context) {
        if (defineDialog == null) {
            DialogUtil dialogUtil = new DialogUtil(context);
        }
        return defineDialog;
    }

    private DialogUtil(Context context) {
        mContext = context;
        builder = new AlertDialog.Builder(mContext);
        View view = ((Activity)mContext).getLayoutInflater().inflate(R.layout.view_dialog_custom, null, false);
        //通过setView将layout设置给alertDialog，并对layout中的view设置click事件
        builder.setView(view);
        builder.setCancelable(false);
        defineDialog = builder.create();

        view.findViewById(R.id.btn_cancle).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                defineDialog.dismiss();
            }
        });

        view.findViewById(R.id.btn_blog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "访问博客", Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse("http://www.baidu.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);
                defineDialog.dismiss();
            }
        });

        view.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "对话框已关闭~", Toast.LENGTH_SHORT).show();
                defineDialog.dismiss();
            }
        });

    }

}
