package com.turman.fb.example.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/2/25.
 */
public class SimpleFragment extends Fragment {

    //这里重写这个回调函数来给我们自定义的fragment绑定一个自定义的布局
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple, container,false);
        return view;
    }
}
