package com.turman.fb.example.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/2/18.
 */
public class PopUpWindowActivity extends AppCompatActivity {

    private Button btn_show;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupwindow);

        mContext = PopUpWindowActivity.this;
        btn_show = (Button) findViewById(R.id.show);

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopWindow(v);
            }
        });
    }

    //自定义的弹出窗
    private void initPopWindow(View v) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_popip, null,false);
        Button btn_xx = (Button) view.findViewById(R.id.btn_xixi);
        Button btn_hh = (Button) view.findViewById(R.id.btn_hehe);

        //构造一个PopupWindow,参数依次是加载的View，宽度高度
        final PopupWindow popupWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true);
        popupWindow.setAnimationStyle(R.anim.window_pop); //设置加载动画

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码，你会发现，当你把PopupWindow显示出来，无论你按多少次后退键
        //popupwindow都不会关闭，且无法退出程序
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                //这里如果返回true，touch事件将被拦截
                //拦截后PopupWindow的ontouchevent不被调用，这样点击外部区域无法dismiss
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        //设置显示的位置
        popupWindow.showAsDropDown(v,50,0);
        btn_xx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "xixi~", Toast.LENGTH_SHORT).show();
            }
        });
        btn_hh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"hehe~",Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
    }
}























