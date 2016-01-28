package com.turman.fb.activity.bean;

import android.view.View;

/**
 * Created by dqf on 2016/1/19.
 */
public class WrapperView {
    private View mTarget;

    public WrapperView(View view) {
        mTarget = view;
    }

    public int getWidth(){
        return mTarget.getLayoutParams().width;
    }

    public void setWidth(int width) {
        mTarget.getLayoutParams().width = width;
        mTarget.requestLayout();
    }
}
