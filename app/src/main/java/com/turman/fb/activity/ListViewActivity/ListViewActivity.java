package com.turman.fb.activity.ListViewActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.turman.fb.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dqf on 2016/2/14.
 */
public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<Animal> mData = null;
    private Context mContext;
    private AnimalAdapter mAdapter = null;
    private ListView list_animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);
        mContext = ListViewActivity.this;
        list_animal = (ListView) findViewById(R.id.list_view);
        mData = new LinkedList<Animal>();
        mData.add(new Animal("狗说", "你是狗么?", R.drawable.obama));
        mData.add(new Animal("牛说", "你是牛么?", R.drawable.road_rage));
        mData.add(new Animal("鸭说", "你是鸭么?", R.drawable.taipei_101));
        mData.add(new Animal("鱼说", "你是鱼么?", R.drawable.world));
        mData.add(new Animal("马说", "你是马么?", R.drawable.obama));
        mAdapter = new AnimalAdapter((LinkedList<Animal>) mData, mContext);
        list_animal.setAdapter(mAdapter);
        list_animal.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mContext, "你点击了第" + position + "项", Toast.LENGTH_SHORT).show();
    }
}
