package com.turman.fb.activity.test;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by dqf on 2016/1/27.
 */
public class DualPaneContainer extends LinearLayout implements Container {
    private MyDetailView detailView;

    public DualPaneContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        detailView = (MyDetailView) getChildAt(1);
        //detailView = new MyDetailView(getContext(),null);
    }

    public boolean onBackPressed() {
        return false;
    }

    @Override public void showItem(String item) {
        detailView.setItem(item);
    }
}