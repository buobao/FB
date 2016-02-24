package com.turman.fb.example.contentprovider;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.turman.fb.R;

import java.util.ArrayList;

/**
 * Created by dqf on 2016/2/24.
 */
public class ContentProviderActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contentprovider);

        Button read_message = (Button) findViewById(R.id.read_message);
        read_message.setOnClickListener(this);
        Button add_message = (Button) findViewById(R.id.add_message);
        add_message.setOnClickListener(this);
        Button query_user = (Button) findViewById(R.id.query_user);
        query_user.setOnClickListener(this);
        Button add_user = (Button) findViewById(R.id.add_user);
        add_user.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.read_message:
                readMessage();
                break;
            case R.id.add_message:
                addMessage();
                break;
            case R.id.query_user:
                queryUser("1655262965");
                break;
            case R.id.add_user:
                try {
                    addContact();
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (OperationApplicationException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void queryUser(String number) {
        Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/"+number);
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri,new String[]{"display_name"},null,null,null);
        if (cursor.moveToFirst()) {
            String name = cursor.getString(0);
            System.out.println(number + "对应的用户是:"+name);
        }
        cursor.close();
    }

    //增加一条短信
    private void addMessage() {
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://sms/");

        ContentValues contentValues = new ContentValues();
        contentValues.put("address","15618605130");
        contentValues.put("type","1");
        contentValues.put("date",System.currentTimeMillis());
        contentValues.put("body","This is message body!!!");
        resolver.insert(uri, contentValues);
        Log.e("HeHe", "短信插入完毕!");
    }

    //读取短信息
    private void readMessage() {
        Uri uri = Uri.parse("content://sms/");
        ContentResolver resolver = getContentResolver();
        //获取的是那些信息
        Cursor cursor = resolver.query(uri, new String[]{"address","date","type","body"},null,null,null);
        while (cursor.moveToNext()) {
            String address = cursor.getString(0);
            String date = cursor.getString(1);
            String type = cursor.getString(2);
            String body = cursor.getString(3);

            System.out.println("地址:"+address);
            System.out.println("时间:"+date);
            System.out.println("类型:"+type);
            System.out.println("内容:"+body);
            System.out.println("=======================");
        }
        cursor.close();
    }

    private void addContact() throws RemoteException, OperationApplicationException {
        //使用事务添加联系人
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri dataUri =  Uri.parse("content://com.android.contacts/data");

        ContentResolver resolver = getContentResolver();
        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
        ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri)
                .withValue("account_name", null)
                .build();
        operations.add(op1);

        //依次是姓名，号码，邮编
        ContentProviderOperation op2 = ContentProviderOperation.newInsert(dataUri)
                .withValueBackReference("raw_contact_id", 0)
                .withValue("mimetype", "vnd.android.cursor.item/name")
                .withValue("data2", "Coder-pig")
                .build();
        operations.add(op2);

        ContentProviderOperation op3 = ContentProviderOperation.newInsert(dataUri)
                .withValueBackReference("raw_contact_id", 0)
                .withValue("mimetype", "vnd.android.cursor.item/phone_v2")
                .withValue("data1", "13798988888")
                .withValue("data2", "2")
                .build();
        operations.add(op3);

        ContentProviderOperation op4 = ContentProviderOperation.newInsert(dataUri)
                .withValueBackReference("raw_contact_id", 0)
                .withValue("mimetype", "vnd.android.cursor.item/email_v2")
                .withValue("data1", "779878443@qq.com")
                .withValue("data2", "2")
                .build();
        operations.add(op4);
        //将上述内容添加到手机联系人中~
        resolver.applyBatch("com.android.contacts", operations);
        Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
    }
}























