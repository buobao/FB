package com.turman.fb.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/1/20.
 */
public class AnimateLayout extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animatelayouttransition);

        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1);
        sa.setDuration(2000);
        // 第二个参数dely ： the delay by which each child's animation must be offset
        LayoutAnimationController lac = new LayoutAnimationController(sa, 0.5F);
        // 设置显示的顺序 这个必须要在dely不为0的时候才有效
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        ll.setLayoutAnimation(lac);
    }
}
