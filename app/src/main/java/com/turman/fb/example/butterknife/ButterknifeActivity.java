package com.turman.fb.example.butterknife;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.turman.fb.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * Created by dqf on 2016/4/12.
 */
public class ButterknifeActivity extends Activity {
    @Bind({R.id.menu_1,R.id.menu_2,R.id.menu_3,R.id.menu_4,R.id.menu_5})
    protected List<TextView> menus;
    @Bind(R.id.img)
    protected ImageView imageView;
    @Bind(R.id.spinner)
    protected Spinner spinner;

    private String[] items = new String[]{"item1","item2","item3","item4","item5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife);
        ButterKnife.bind(this);

        imageView.setImageDrawable(getResources().getDrawable(R.drawable.obama));

        for (TextView textView:menus) {
            textView.setTextColor(getResources().getColor(android.R.color.holo_orange_light));
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @OnItemSelected(value = R.id.spinner, callback = OnItemSelected.Callback.ITEM_SELECTED)
    protected void itemClicked(int position){
        Toast.makeText(ButterknifeActivity.this,items[position],Toast.LENGTH_SHORT).show();
    }

    @OnItemSelected(value = R.id.spinner, callback = OnItemSelected.Callback.NOTHING_SELECTED)
    protected void nothingClicked(){
        Toast.makeText(this, "Nothing", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.menu_1,R.id.menu_2,R.id.menu_3,R.id.menu_4,R.id.menu_5})
    protected void menuClicked(View v){
        String msg = "";
        switch (v.getId()) {
            case R.id.menu_1:
                msg = "menu 1";
                break;
            case R.id.menu_2:
                msg = "menu 2";
                break;
            case R.id.menu_3:
                msg = "menu 3";
                break;
            case R.id.menu_4:
                msg = "menu 4";
                break;
            case R.id.menu_5:
                msg = "menu 5";
                break;
        }
        Toast.makeText(ButterknifeActivity.this, msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
