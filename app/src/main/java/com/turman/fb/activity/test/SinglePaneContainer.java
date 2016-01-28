package com.turman.fb.activity.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/1/27.
 */
public class SinglePaneContainer extends FrameLayout implements Container {
    private ItemListView listView;

    public SinglePaneContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        listView = (ItemListView) getChildAt(0);
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
        //MyDetailView detailView = new MyDetailView(getContext(),null);
        detailView.setItem(item);
    }

    private boolean listViewAttached() {
        return listView.getParent() != null;
    }
}