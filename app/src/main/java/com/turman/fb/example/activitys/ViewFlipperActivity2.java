package com.turman.fb.example.activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/2/16.
 */
public class ViewFlipperActivity2 extends AppCompatActivity {

    private Context mContext;
    private ViewFlipper vflp_help;

    private int[] resId = {R.drawable.obama,R.drawable.world,R.drawable.road_rage,
        R.drawable.world
    };

    private final static int MIN_MOVE = 200; //最小距离
    private MyGestureListener mgListener;
    private GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewflipper2);
        mContext = ViewFlipperActivity2.this;
        mgListener = new MyGestureListener();
        mDetector = new GestureDetector(this, mgListener);
        vflp_help = (ViewFlipper) findViewById(R.id.vif);

        for (int i=0;i<resId.length;i++) {
            vflp_help.addView(getImageView(resId[i]));
        }
    }

    private View getImageView(int i) {
        ImageView img = new ImageView(this);
        img.setImageResource(i);

        return img;
    }

    //这里重写onTouchEvent出发MyGestureListener中的方法
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > MIN_MOVE) {
                vflp_help.setInAnimation(mContext, R.anim.right_in);
                vflp_help.setOutAnimation(mContext, R.anim.right_out);
                vflp_help.showNext();
            } else if (e2.getX() - e1.getX() > MIN_MOVE) {
                vflp_help.setInAnimation(mContext,R.anim.left_in);
                vflp_help.setOutAnimation(mContext, R.anim.left_out);
                vflp_help.showPrevious();
            }
            return true;
        }
    }
}






















