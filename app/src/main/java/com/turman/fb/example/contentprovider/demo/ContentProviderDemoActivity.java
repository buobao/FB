package com.turman.fb.example.contentprovider.demo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/2/24.
 */
public class ContentProviderDemoActivity extends Activity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlybutton);
        btn = (Button)findViewById(R.id.btn);

        final ContentResolver resolver = this.getContentResolver();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();

                values.put("name","测试");
                Uri uri = Uri.parse("content://com.turman.fb.example.contentprovider.demo.myprovider/test");
                resolver.insert(uri,values);
                Toast.makeText(getApplicationContext(),"数据插入成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
