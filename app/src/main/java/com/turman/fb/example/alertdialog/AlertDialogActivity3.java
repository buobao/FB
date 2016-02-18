package com.turman.fb.example.alertdialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/2/18.
 */
public class AlertDialogActivity3 extends AppCompatActivity implements View.OnClickListener {

    private Button btn_one;
    private Button btn_two;
    private Button btn_three;

    private ProgressDialog pd1 = null;
    private ProgressDialog pd2 = null;

    private final static int MAXVALUE = 100;
    private int progressStart = 0;
    private int add = 0;
    private Context mContext = null;

    //定义一个用于更新进度的Handler，因为只能由主线程更新界面，所以要用Handler传递信息
    final Handler hand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what ==123) {
                //设置进度条的当前值
                pd2.setProgress(progressStart);
            }
            //如果当前大于或等于进度条的最大值，调用dismiss方法关闭对话框
            if (progressStart >= MAXVALUE) {
                pd2.dismiss();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertdialog3);

        mContext = AlertDialogActivity3.this;

        bindViews();
    }

    private void bindViews() {
        btn_one = (Button) findViewById(R.id.one);
        btn_two = (Button) findViewById(R.id.two);
        btn_three = (Button) findViewById(R.id.three);

        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.one:
                ProgressDialog.show(AlertDialogActivity3.this,"资源加载中","资源加载中，请稍后...",false,true);
                break;
            case R.id.two:
                pd1 = new ProgressDialog(mContext);
                //依次设置标题，内容，是否用取消按钮关闭，是否显示进度条
                pd1.setTitle("软件更新中");
                pd1.setMessage("软件正在更新，请稍后...");
                pd1.setCancelable(true);
                //这里是设置进度条的风格,HORIZONTAL是水平进度条，SPINNER是圆形进度条
                pd1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd1.setIndeterminate(true);
                //调用show方法将ProgressDialog显示出来
                pd1.show();
                break;
            case R.id.three:
                progressStart = 0;
                add = 0;

                //依次设置一些属性
                pd2 = new ProgressDialog(mContext);
                pd2.setMax(MAXVALUE);
                pd2.setTitle("文件读取中");
                pd2.setMessage("文件加载中,请稍后...");
                //这里设置为不可以通过按取消按钮关闭进度条
                pd2.setCancelable(false);
                pd2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //这里设置的是是否显示进度,设为false才是显示的哦！
                pd2.setIndeterminate(false);
                pd2.show();
                //这里的话新建一个线程,重写run()方法,
                new Thread()
                {
                    public void run()
                    {
                        while(progressStart < MAXVALUE)
                        {
                            //这里的算法是决定进度条变化的,可以按需要写
                            progressStart = 2 * usetime() ;
                            //把信息码发送给handle让更新界面
                            hand.sendEmptyMessage(123);
                        }
                    }
                }.start();
                break;
        }
    }

    //这里设置一个耗时的方法:
    private int usetime() {
        add++;
        try{
            Thread.sleep(100);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return add;
    }
}


































































