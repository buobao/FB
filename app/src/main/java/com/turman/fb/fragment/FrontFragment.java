package com.turman.fb.fragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/1/8.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FrontFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_swip,container,false);
    }
}
