package com.turman.fb.activity.demo.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.turman.fb.R;
import com.turman.fb.activity.demo.action.LayoutAction;
import com.turman.fb.activity.test.MyDetailView;

/**
 * Created by dqf on 2016/1/28.
 */
public class MyListLayout extends FrameLayout implements LayoutAction {
    private ListView listView;

    public MyListLayout(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    public void setListView(ListView listView){
        this.listView = listView;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        listView = (ListView)getChildAt(0);
    }

    public boolean onBackPressed() {
        if (!listViewAttached()) {
            removeViewAt(0);
            addView(listView);
            return true;
        }
        return false;
    }

    @Override
    public void showItem(String item) {
        if (listViewAttached()) {
            removeViewAt(0);
            View.inflate(getContext(), R.layout.detail, this);
        }

        MyDetailView detailView = (MyDetailView) getChildAt(0);
        detailView.setItem(item);
    }


    private boolean listViewAttached() {
        return listView.getParent() != null;
    }
}
