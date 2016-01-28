package com.turman.fb.activity.demo.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.turman.fb.activity.demo.DemoActivity;
import com.turman.fb.activity.demo.action.LayoutAction;

import java.util.ArrayList;

/**
 * Created by dqf on 2016/1/28.
 */
public class ProjectListView extends ListView {
    ArrayList<String> list1 = new ArrayList<String>();

    public ProjectListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        list1.add("project1");
        list1.add("project2");
        list1.add("project3");
        list1.add("project4");
        list1.add("project5");
        list1.add("project5");
        list1.add("project5");
        list1.add("project5");
        list1.add("project5");
        list1.add("project5");
        list1.add("project5");
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        final ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,list1);
        setAdapter(itemsAdapter);
        setOnItemClickListener(new OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view,
                                              int position, long id) {
                String item = itemsAdapter.getItem(position);
                DemoActivity activity = (DemoActivity) getContext();
                LayoutAction container = activity.getContainer();
                container.showItem(item);
            }
        });
    }
}