package com.turman.fb.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.turman.fb.R;
import com.turman.fb.fragment.ArticleFragment;
import com.turman.fb.fragment.HeadlinesFragment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HeadlinesFragment.FragmentSelectListener {

    private boolean show_flag;
    private HeadlinesFragment headlinesFragment;
    private ArticleFragment articleFragment;
    private String app_name;

    private static final int PICK_IMAGE = 1;
    private static final int PICK_IMAGE_CAMERA = 2;

    private NfcAdapter nfcAdapter;
    private boolean androidBeamAvailable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        show_flag = false;
        app_name = "Get this attribute from MainActivity!";
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null){

            //这里判断该参数的值，当该值不为空时，则表明该activity在上次退出后保存了相应的运行状态，则不需要再创建相应的fragment
            if (savedInstanceState != null){
                return;
            }

            headlinesFragment = new HeadlinesFragment();
            Bundle bundle = new Bundle();
            bundle.putString("message",app_name);
            headlinesFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,headlinesFragment).commit();

            articleFragment = new ArticleFragment();
            articleFragment.setArguments(getIntent().getExtras());
        }

        Button btn = (Button) findViewById(R.id.btn_change_fragment);

        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show_flag) {
                    //这里添加addtobackstack可以使app在点击返回按钮时返回上一个fragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, headlinesFragment).addToBackStack(null).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, articleFragment).addToBackStack(null).commit();
                }
                show_flag = !show_flag;
            }
        });

        //保存数据到preferences
        SharedPreferences sharedPreferences = getSharedPreferences("setting", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("message", "Get this message from preferences!");
        editor.commit();

        Button btn_call = (Button) findViewById(R.id.btn_call);
        btn_call.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
//                Uri number = Uri.parse("tel:110");
//                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
//                Intent chooser = Intent.createChooser(callIntent, "Select");
//                startActivity(chooser);

                //发送文本信息
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_SEND);
//                intent.putExtra(Intent.EXTRA_STREAM, "");
//                intent.setType("text/plain");
//                startActivity(Intent.createChooser(intent, "DQF"));

                //从相册获取照片
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent, MainActivity.PICK_IMAGE);

                //从照相机获取照片
                String state = Environment.getExternalStorageState();
                if (state.equals(Environment.MEDIA_MOUNTED)){
                    Intent getImage = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivityForResult(getImage, MainActivity.PICK_IMAGE_CAMERA);
                } else {
                    Toast.makeText(getApplicationContext(), "请确认已经插入SD卡", Toast.LENGTH_LONG).show();
                }

            }
        });

        Button btn_map = (Button) findViewById(R.id.btn_map);
        btn_map.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW,location);
                startActivity(mapIntent);
            }
        });

        Button btn_web = (Button) findViewById(R.id.btn_web);
        btn_web.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.baidu.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(webIntent);
            }
        });

        Button btn_mail = (Button) findViewById(R.id.btn_mail);
        btn_mail.setOnClickListener(new Button.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            @Override
            public void onClick(View v) {
//                Intent mailIntent = new Intent(Intent.ACTION_SEND);
//                mailIntent.setType();
//                mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"jon@example.com"});
//                mailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
//                mailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
//                mailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));

                Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(2012, 0, 19, 7, 30);
                Calendar endTime = Calendar.getInstance();
                endTime.set(2012, 0, 19, 10, 30);
                calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
                calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
                calendarIntent.putExtra(CalendarContract.Events.TITLE, "Ninja class");
                calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Secret dojo");

                //验证intent能不能得到处理
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(calendarIntent,0);
                if (activities.size() > 0) {
                    startActivity(calendarIntent);
                }
            }
        });

    }


    //这里处理使用其他app应用操作返回的结果处理，如选择相册照片后的返回处理
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //这里的requestCode是处理对应的发起请求的处理，这里需要区分。
        if (requestCode == MainActivity.PICK_IMAGE){
            Uri uri = data.getData();
            Toast.makeText(this,uri.toString(),Toast.LENGTH_SHORT).show();
        } else if (requestCode == MainActivity.PICK_IMAGE_CAMERA){
            Uri uri = data.getData();
            if (uri == null){
                Bundle bundle = data.getExtras();
                if (bundle != null){
                    Bitmap photo = (Bitmap)bundle.get("data");
                    //saveImage(photo,)
                }
            }
        } else {
            Toast.makeText(this,"Other action!",Toast.LENGTH_SHORT).show();
        }

    }


    public static boolean saveImage(Bitmap photo, String spath){
        try{
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(spath,false));
            photo.compress(Bitmap.CompressFormat.JPEG,100,bos);
            bos.flush();
            bos.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public File getAlbumStorageDir(String albumName) {
// Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("DQF", "Directory not created");
        }
        return file;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSelect() {

    }
}
