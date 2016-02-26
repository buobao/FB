package com.turman.fb.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dqf on 2016/2/26.
 */
public class MyDBOpenHelper extends SQLiteOpenHelper {

    /**
     * 这里定义了一个版本号为1
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public MyDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {super(context, name, null, 1); }

    /**
     * 创建回调
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE person(personid INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20))");
    }

    /**
     * 更新回调,版本号发生变更时的更新操作
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE person ADD phone VARCHAR(12) NULL");
    }
}
