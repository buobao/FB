package com.turman.fb.example.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.widget.Toast;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/2/25.
 */
public class SimpleFragmentActivity extends Activity {

    private String name = "step 1";

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        //这里依据长宽判断当前是横屏还是竖屏以加载不同的fragment
        Display dis = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        dis.getSize(size);
        Bundle bundle = new Bundle();
        bundle.putString("param",name);
        if (size.x > size.y) {
            SimpleFragment f1 = new SimpleFragment();
            f1.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.frg,f1).commit();
            f1.getDate(new SimpleFragment.CallBack() {
                @Override
                public void getResult(String result) {
                    Toast.makeText(SimpleFragmentActivity.this,"返回的参数："+result,Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            SimpleFragment1 f2 = new SimpleFragment1();
            f2.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.frg,f2).commit();
        }
    }
}
