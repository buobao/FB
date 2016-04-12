package com.turman.fb.activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.turman.fb.R;
import com.turman.fb.activity.bean.WrapperView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dqf on 2016/1/12.
 */
public class AsyncTaskActivity extends Activity {

    @Bind(R.id.imageView)
    protected ImageView mImageView;
    @Bind(R.id.button)
    protected Button mButton;
    @Bind(R.id.progressBar)
    protected ProgressBar mProgressBar;
    @Bind(R.id.input)
    protected EditText editText;
    @Bind(R.id.btn_show)
    protected Button mbuttonShow;
    @Bind(R.id.textView_1)
    protected TextView textView_1;
    @Bind(R.id.textView_2)
    protected TextView textView_2;
    @Bind(R.id.btn_show1)
    protected Button mbuttonShow1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_imagedown);
        ButterKnife.bind(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetCSDNLogoTask task = new GetCSDNLogoTask();
                task.execute("http://d.hiphotos.baidu.com/image/h%3D200/sign=a054e736484a20a42e1e3bc7a0529847/0df431adcbef7609a87426bb29dda3cc7cd99e93.jpg");
            }
        });

        //检测当前设备有没有相应的特性
        PackageManager pm = getPackageManager();
        if (!pm.hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS)) {
            //如果没有指南针，则处理
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onResume() {
        super.onResume();
        //这里添加的动画,时间单位毫秒
        //ObjectAnimator.ofFloat(mButton, "rotationX", 0.0f, 360.0f).setDuration(1000).start();
        //ObjectAnimator.ofFloat(editText, "rotationY",0.0f, 360.0f).setDuration(1000).start();

        ObjectAnimator anim = ObjectAnimator.ofFloat(mButton,"xxx",0.0f,1.0f)
                .setDuration(1000);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (float) animation.getAnimatedValue();
                mButton.setAlpha(cVal);
                mButton.setScaleX(cVal);
                mButton.setScaleY(cVal);
            }
        });

        anim.start();

        //下面使用的是一个包装类使按钮拥有width属性在使用动画时可以使用这个属性，包装类的定义见WrapperView
        WrapperView wrapperView = new WrapperView(mbuttonShow);
        ObjectAnimator.ofInt(wrapperView, "width", 500)
                .setDuration(3000)
                .start();

        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f);
        ObjectAnimator.ofPropertyValuesHolder(textView_1, pvhX, pvhY, pvhZ)
                .setDuration(2000).start();

        final ValueAnimator animator1 = ValueAnimator.ofFloat(0,600);
        animator1.setTarget(mbuttonShow1);
        animator1.setDuration(1000).start();
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
//                float x = 0,y = 0;
//                mbuttonShow1.getOffsetForPosition(x,y);
//                System.out.println("value:" + value);
                mbuttonShow1.setTranslationX(value);
            }
        });

//        Animator anim2 = AnimatorInflater.loadAnimator(this, R.anim.mydefine);
//        anim2.setTarget(textView_2);
//        anim2.start();

    }

    private class GetCSDNLogoTask extends AsyncTask<String, Integer, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            publishProgress(0);
            //模拟一个http客户端
            HttpClient ahc = new DefaultHttpClient();
            publishProgress(20);
            //创建一个GET模拟请求
            HttpGet hg = new HttpGet(params[0]);

            final Bitmap bm;

            try{
                //这里创建一个response对象，以获取请求得到的数据
                HttpResponse hr = ahc.execute(hg);
                publishProgress(50);
                //得到图片数据并转化
                bm = BitmapFactory.decodeStream(hr.getEntity().getContent());
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }

            publishProgress(100);
            return bm;
        }

        protected void onProgressUpdate(Integer... progress){
            mProgressBar.setProgress(progress[0]);
        }

        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                Toast.makeText(AsyncTaskActivity.this,"获取图片成功",Toast.LENGTH_SHORT).show();
                //将结果显示到view
                mImageView.setImageBitmap(result);
                mProgressBar.setVisibility(View.GONE);
            } else {
                Toast.makeText(AsyncTaskActivity.this,"获取图片失败",Toast.LENGTH_SHORT).show();
            }
        }

        protected void onPreExecute(){
            mImageView.setImageBitmap(null);
            mProgressBar.setProgress(0);
            mProgressBar.setVisibility(View.VISIBLE);
        }

        protected  void onCancelled(){
            mProgressBar.setProgress(0);
            mProgressBar.setVisibility(View.GONE);
        }

    }
}

























