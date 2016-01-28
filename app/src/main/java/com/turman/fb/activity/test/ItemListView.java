package com.turman.fb.activity.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by dqf on 2016/1/27.
 */
public class ItemListView extends ListView {
    ArrayList<String> list1 = new ArrayList<String>();

    public ItemListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        list1.add("pfg");
        list1.add("124");
        list1.add("234");
        list1.add("456");
        list1.add("567");
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        final ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,list1);
        setAdapter(itemsAdapter);
        setOnItemClickListener(new OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view,
                                              int position, long id) {
                String item = itemsAdapter.getItem(position);
                TestActivity activity = (TestActivity) getContext();
                Container container = activity.getContainer();
                container.showItem(item);
            }
        });
    }
}