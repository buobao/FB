package com.turman.fb.example.toast;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/2/17.
 */
public class ToastActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        Button btn = (Button) findViewById(R.id.show_toast);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                midToast("This is a toast!", Toast.LENGTH_SHORT);
            }
        });

        Button btn1 = (Button) findViewById(R.id.show_toast1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                midToast2("This is a toast layout with a image!", Toast.LENGTH_SHORT);
            }
        });

        Button btn2 = (Button) findViewById(R.id.show_toast2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                midToast3("This is a toast layout with a image!",Toast.LENGTH_SHORT);
            }
        });
    }


    //自定义一个toast用于显示
    public void midToast(String str, int showTime) {
        Toast toast = Toast.makeText(ToastActivity.this,str,showTime);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.YELLOW);
        toast.show();
    }

    //自定义一个带图片的toast
    public void midToast2(String str, int showTime) {
        Toast toast = Toast.makeText(ToastActivity.this, str, showTime);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
        LinearLayout layout = (LinearLayout) toast.getView();

        //背景色
        layout.setBackgroundColor(Color.BLUE);
        //新建一个imageview。并添加到toast的layout中
        ImageView image = new ImageView(this);
        image.setImageResource(R.mipmap.ic_launcher);
        layout.addView(image, 0);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.YELLOW);
        toast.show();
    }

    //完全自定义布局的toast
    public void midToast3(String str, int showTime) {
        LayoutInflater inflater = getLayoutInflater();
        //这里的第二个参数是什么意思
        View v = inflater.inflate(R.layout.view_toast_custom, (ViewGroup) findViewById(R.id.lly_toast));
        TextView tv_msg = (TextView) v.findViewById(R.id.tv_msg);
        ImageView img_logo = (ImageView) v.findViewById(R.id.img_logo);
        tv_msg.setText(str);
        Toast toast = new Toast(ToastActivity.this);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(showTime);
        toast.setView(v);
        toast.show();
    }
}
















