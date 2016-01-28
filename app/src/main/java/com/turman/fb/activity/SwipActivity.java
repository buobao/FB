package com.turman.fb.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.turman.fb.R;
import com.turman.fb.fragment.FrontFragment;
import com.turman.fb.fragment.ImageFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by dqf on 2016/1/8.
 */
public class SwipActivity extends Activity implements View.OnClickListener {

    private boolean mShowingBack = false;

    @InjectView(R.id.container)
    protected View container;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        ButterKnife.inject(this);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, new FrontFragment()).commit();
        }
        container.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void flipCard(){
        mShowingBack = !mShowingBack;

        if (!mShowingBack) {
            getFragmentManager().popBackStack();
            return;
        }

        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.card_flip_right_in, R.anim.card_flip_right_out,
                        R.anim.card_flip_left_in, R.anim.card_flip_left_out)
                .replace(R.id.container, new ImageFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.container:
                flipCard();
                break;
        }
    }
}


























