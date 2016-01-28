package com.turman.fb.activity.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.turman.fb.R;
import com.turman.fb.activity.demo.action.LayoutAction;
import com.turman.fb.activity.demo.layout.MyListLayout;

/**
 * Created by dqf on 2016/1/28.
 */
public class DemoActivity extends Activity implements TextView.OnClickListener {

    private LayoutAction container;

    private TextView tab1,tab2,tab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity);

        container = (LayoutAction) findViewById(R.id.container);
        tab1 = (TextView) findViewById(R.id.tab1);
        tab2 = (TextView) findViewById(R.id.tab2);
        tab3 = (TextView) findViewById(R.id.tab3);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
    }

    public LayoutAction getContainer() {
        return container;
    }

    @Override
    public void onBackPressed() {
        boolean handled = container.onBackPressed();
        if (!handled) {
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        ((FrameLayout)container).removeViewAt(0);
        switch (v.getId()) {
            case R.id.tab1:
                Toast.makeText(this,"messages",Toast.LENGTH_SHORT).show();
                View.inflate(this, R.layout.message_list_view, (FrameLayout) container);
                break;
            case R.id.tab2:
                Toast.makeText(this,"projects",Toast.LENGTH_SHORT).show();
                View.inflate(this, R.layout.project_list_view, (FrameLayout) container);
                break;
            case R.id.tab3:
                Toast.makeText(this,"others",Toast.LENGTH_SHORT).show();
                View.inflate(this, R.layout.other_list_view, (FrameLayout) container);
                break;
        }
        ((MyListLayout)container).setListView((ListView)((MyListLayout)container).getChildAt(0));
    }
}
