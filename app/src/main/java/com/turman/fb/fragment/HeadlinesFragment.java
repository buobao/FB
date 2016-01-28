package com.turman.fb.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.turman.fb.R;

/**
 * Created by dqf on 2015/12/31.
 */
public class HeadlinesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String message = bundle.getString("message");
        View view = inflater.inflate(R.layout.fragment_headlines, container, false);
        TextView textView = (TextView) view.findViewById(R.id.message);
        textView.setText(message);

        //读取preferences文件中的数据
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("setting",Context.MODE_PRIVATE);
        String preferences_message = sharedPreferences.getString("message", "defualt");
        TextView preferences_text = (TextView) view.findViewById(R.id.preferences_message);
        preferences_text.setText(preferences_message);

        return view;
    }

    public interface FragmentSelectListener {
        public void onSelect();
    }

    private FragmentSelectListener fragmentSelectListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        fragmentSelectListener = (FragmentSelectListener) context;
    }
}
