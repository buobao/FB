package com.turman.fb.example.actionbaractivity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/4/7.
 */
public class ActionActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ImageView mBannner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionbar);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mBannner = (ImageView) findViewById(R.id.bannner);
        setSupportActionBar(mToolbar);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        mCollapsingToolbarLayout.setTitle("测试");
    }
}
