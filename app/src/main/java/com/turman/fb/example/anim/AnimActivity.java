package com.turman.fb.example.anim;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.turman.fb.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/4/12.
 */
public class AnimActivity extends Activity {

    @Bind(R.id.btn_start)
    Button btnStart;
    @Bind(R.id.btn_stop)
    Button btnStop;
    @Bind(R.id.img_show)
    ImageView imgShow;

    private AnimationDrawable anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        ButterKnife.bind(this);

        anim = (AnimationDrawable) imgShow.getBackground();
    }

    @OnClick({R.id.btn_start, R.id.btn_stop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                anim.start();
                break;
            case R.id.btn_stop:
                anim.stop();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
