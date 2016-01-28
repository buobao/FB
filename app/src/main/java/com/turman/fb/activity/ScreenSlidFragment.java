package com.turman.fb.activity;

import android.annotation.TargetApi;
import android.support.v4.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/1/7.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ScreenSlidFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater. inflate(R.layout.fragment_screenslid, container,false);

        return rootView;
    }
}
