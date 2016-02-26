package com.turman.fb.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/2/26.
 */
public class SqliteActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private Button btn_insert;
    private Button btn_query;
    private Button btn_update;
    private Button btn_delete;

    private SQLiteDatabase db;
    private MyDBOpenHelper myDBOpenHelper;
    private StringBuilder sb;
    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        btn_delete = (Button) findViewById(R.id.delete);
        btn_update = (Button) findViewById(R.id.update);
        btn_insert = (Button) findViewById(R.id.insert);
        btn_query = (Button) findViewById(R.id.query);

        btn_delete.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_insert.setOnClickListener(this);
        btn_query.setOnClickListener(this);
        mContext = SqliteActivity.this;

        myDBOpenHelper = new MyDBOpenHelper(mContext, "my.db", null, 1);
    }

    @Override
    public void onClick(View v) {
        db = myDBOpenHelper.getWritableDatabase();
        switch (v.getId()) {
            case R.id.insert:
                ContentValues values1 = new ContentValues();
                values1.put("name","buobao"+i);
                i++;
                db.insert("person",null, values1);
                Toast.makeText(mContext, "插入完毕~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.query:
                sb = new StringBuilder();

                Cursor cursor = db.query("person",null,null,null,null,null,null);
                if (cursor.moveToFirst()) {
                    do {
                        int pid = cursor.getInt(cursor.getColumnIndex("personid"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        sb.append("id：" + pid + ",name：" + name + "\n");
                    } while (cursor.moveToNext());
                }
                cursor.close();
                Toast.makeText(mContext, sb.toString(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.update:
                ContentValues values2 = new ContentValues();
                values2.put("name", "嘻嘻~");
                //参数依次是表名，修改后的值，where条件，以及约束，如果不指定三四两个参数，会更改所有行
                db.update("person", values2, "name = ?", new String[]{"buobao1"});
                Toast.makeText(mContext, "更新成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                //参数依次是表名，以及where条件与约束
                db.delete("person", "personid = ?", new String[]{"3"});
                break;
        }
    }
}


























