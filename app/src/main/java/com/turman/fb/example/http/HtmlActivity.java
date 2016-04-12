package com.turman.fb.example.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.turman.fb.R;
import com.turman.fb.example.newhttp.HttpUtils;
import com.turman.fb.example.newhttp.NatureHttp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dqf on 2016/2/26.
 */
public class HtmlActivity extends AppCompatActivity {

    private TextView txtMenu,txtShow;
    private ImageView imgPic;
    private WebView webView;
    private ScrollView scroll;
    private Bitmap bitmap;
    private String detail = "";
    private boolean flag = false;
    private final static String PIC_URL = "http://scimg.jb51.net/allimg/160317/14-16031G13220936.jpg";
    private final static String HTML_URL = "http://api2.juheapi.com/alexa/historical";

    //用于刷新页面
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x001:
                    hideAllWiget();
                    imgPic.setVisibility(View.VISIBLE);
                    imgPic.setImageBitmap(bitmap);
                    Toast.makeText(HtmlActivity.this, "图片加载完毕", Toast.LENGTH_SHORT).show();
                    break;
                case 0x002:
                    hideAllWiget();
                    scroll.setVisibility(View.VISIBLE);
                    txtShow.setText(detail);
                    Toast.makeText(HtmlActivity.this, "HTML代码加载完毕", Toast.LENGTH_SHORT).show();
                    break;
                case 0x003:
                    hideAllWiget();
                    webView.setVisibility(View.VISIBLE);
                    webView.loadDataWithBaseURL(null, detail, "text/html", "UTF-8", null);
                    Toast.makeText(HtmlActivity.this,"网页加载完毕",Toast.LENGTH_SHORT).show();
                    break;
                default:break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        setView();
    }

    private void setView() {
        txtMenu = (TextView) findViewById(R.id.txtMenu);
        txtShow = (TextView) findViewById(R.id.txtshow);
        imgPic = (ImageView) findViewById(R.id.imgPic);
        webView = (WebView) findViewById(R.id.webView);
        scroll = (ScrollView) findViewById(R.id.scroll);
        registerForContextMenu(txtMenu);
    }

    //定义一个隐藏所有控件的方法
    private void hideAllWiget(){
        imgPic.setVisibility(View.GONE);
        scroll.setVisibility(View.GONE);
        webView.setVisibility(View.GONE);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menus, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    //点击上下文菜单
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.one:

                new Thread(){
                    @Override
                    public void run() {
                        HttpUtils.getInstance(HtmlActivity.this).loadImage(PIC_URL,imgPic);
//                        try {
//                            byte[] data = GetData.getImage(PIC_URL);
//                            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        handler.sendEmptyMessage(0x001);
//                        hideAllWiget();
//                        imgPic.setVisibility(View.VISIBLE);
//                        imgPic.setImageBitmap(bitmap);
//                        Toast.makeText(HtmlActivity.this, "图片加载完毕", Toast.LENGTH_SHORT).show();
                    }
                }.start();
                break;
            case R.id.two:
                new Thread() {
                    public void run() {
//                        try {
//                            detail = GetData.getHtml(HTML_URL);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("site","baidu.com");
                        params.put("key","5aa99d050566a3c1564678d7b13f3352");
                        params.put("start","2016-01-01");
                        params.put("range", "1");
                        String result = NatureHttp.sendGet(HTML_URL,params);
                        System.out.println("This is net work get data result :"+result);

                        //解析json
                        try {
                            JSONObject object = new JSONObject(result);
                            System.out.println("error_code:"+object.getInt("error_code"));
                            System.out.println("reason:"+object.getString("reason"));
                            System.out.println("result:"+object.getString("result"));

                            JSONObject obj = new JSONObject(object.getString("result"));
                            JSONArray arr = obj.getJSONArray("data");
                            for (int i=0;i<arr.length();i++){
                                JSONObject obj1 = arr.getJSONObject(i);
                                System.out.println("i:"+i);
                                System.out.println("date:"+obj1.getString("date"));
                                System.out.println("id:"+obj1.getString("id"));
                                System.out.println("rank:"+obj1.getString("rank"));
                                System.out.println("site:"+obj1.getString("site"));
                                JSONObject sub1 = obj1.getJSONObject("pageViews");
                                System.out.println("perMillion:"+sub1.getString("perMillion"));
                                System.out.println("perUser:"+sub1.getString("perUser"));
                                JSONObject sub2 = obj1.getJSONObject("reach");
                                System.out.println("perMillion:"+sub2.getString("perMillion"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        handler.sendEmptyMessage(0x002);
                    }
                }.start();
                break;
            case R.id.three:
                if (detail.equals("")) {
                    Toast.makeText(HtmlActivity.this, "先请求HTML先嘛~", Toast.LENGTH_SHORT).show();
                } else {
                    handler.sendEmptyMessage(0x003);
                }
                break;
        }

        return true;
    }
}
























