package com.turman.fb.activity.test;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by dqf on 2016/1/27.
 */
public class DetailPresenter {

    private MyDetailView view;

    public void setView(MyDetailView view) {
        this.view = view;
    }

    public void buttonClicked() {
        Toast.makeText(view.getContext(),"clicked",Toast.LENGTH_SHORT).show();
        ((Activity)view.getContext()).onBackPressed();
    }
}
