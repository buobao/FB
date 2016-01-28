package com.turman.fb.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/1/20.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AnimateLayoutTransition extends Activity {

    private LinearLayout ll;

    private LayoutTransition mTransition = new LayoutTransition();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animatelayouttransition);

        ll = (LinearLayout) findViewById(R.id.ll);
        setupCustomAnimation();
        ll.setLayoutTransition(mTransition);
    }

    public void add(View view){
        final Button button = new Button(this);
        ll.addView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll.removeView(button);
            }
        });
    }

    //生成自定义动画
    public void setupCustomAnimation(){
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 1);
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top",0,1);
        PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0, 1);
        PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("bottom", 0, 1);

        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f);

        final ObjectAnimator changeIn  = ObjectAnimator.ofPropertyValuesHolder(this,pvhLeft,
                pvhTop,pvhRight,pvhBottom,pvhScaleX,pvhScaleY)
                .setDuration(mTransition.getDuration(LayoutTransition.CHANGE_APPEARING));
        mTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, changeIn);
        changeIn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                View view = (View) ((ObjectAnimator) animation).getTarget();
                view.setScaleX(1f);
                view.setScaleY(1f);
            }
        });

        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(0.9999f, 360f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);

        final ObjectAnimator changeOut = ObjectAnimator.ofPropertyValuesHolder(this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhRotation)
                .setDuration(mTransition.getDuration(LayoutTransition.DISAPPEARING));

        mTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,changeOut);
        changeOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                View view = (View) ((ObjectAnimator)animation).getTarget();
                view.setRotation(0f);
            }
        });

        ObjectAnimator animIn = ObjectAnimator.ofFloat(null,"rotationY",90f,0f)
                .setDuration(mTransition.getDuration(LayoutTransition.APPEARING));
        animIn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                View view = (View) ((ObjectAnimator)animation).getTarget();
                view.setRotationY(0f);
            }
        });

        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "rotationX",0f,90f)
                .setDuration(mTransition.getDuration(LayoutTransition.DISAPPEARING));
        mTransition.setAnimator(LayoutTransition.DISAPPEARING,animOut);
        animOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                View view = (View) ((ObjectAnimator)animation).getTarget();
                view.setRotationX(0f);
            }
        });
    }

}


















