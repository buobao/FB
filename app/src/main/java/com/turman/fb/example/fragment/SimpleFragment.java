package com.turman.fb.example.fragment;

import android.animation.Animator;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/2/25.
 */
public class SimpleFragment extends Fragment {

    private String name = "step 2";

    public interface CallBack{
        public void getResult(String result);
    }

    public void getDate(CallBack callBack){
        callBack.getResult(name);
    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        Bundle bundle = getArguments();
        Toast.makeText(getActivity(),"获取到的字符是："+bundle.getString("param"),Toast.LENGTH_SHORT).show();
        return super.onCreateAnimator(transit, enter, nextAnim);
    }

    //这里重写这个回调函数来给我们自定义的fragment绑定一个自定义的布局
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple, container,false);
        return view;
    }
}
