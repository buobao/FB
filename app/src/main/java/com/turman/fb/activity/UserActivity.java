package com.turman.fb.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.turman.fb.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dqf on 2016/2/14.
 */
public class UserActivity extends AppCompatActivity {

    private String[] names = new String[]{"抖动妹","干蛋哥","二蛋"};
    private String[] says = new String[]{"抖动中生存，抖抖抖@！","我是干蛋，我就干蛋！","二蛋的世界，你们不懂！"};
    private int[] imgIds = new int[]{R.drawable.cst_logo, R.drawable.obama,R.drawable.road_rage};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);

        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();

        for (int i=0; i<names.length; i++) {
            Map<String,Object> showitem = new HashMap<String, Object>();

            showitem.put("touxiang",imgIds[i]);
            showitem.put("name",names[i]);
            showitem.put("says",says[i]);
            listitem.add(showitem);
        }

        SimpleAdapter myAdapter = new SimpleAdapter(getApplicationContext(), listitem, R.layout.item_layout, new String[]{"touxiang","name","says"},new int[]{R.id.imgtou,R.id.name,R.id.says});
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(myAdapter);
    }
}
