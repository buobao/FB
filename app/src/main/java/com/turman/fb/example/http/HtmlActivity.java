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
    private final static String PIC_URL = "http://ww2.sinaimg.cn/large/7a8aed7bgw1evshgr5z3oj20hs0qo0vq.jpg";
    private final static String HTML_URL = "http://www.qq.com";

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
                    webView.loadDataWithBaseURL("", detail, "text/html", "UTF-8", "");
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
        inflater.inflate(R.menu.menus,menu);
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
                        try {
                            byte[] data = GetData.getImage(PIC_URL);
                            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0x001);
                    }
                }.start();
                break;
            case R.id.two:
                new Thread() {
                    public void run() {
                        try {
                            detail = GetData.getHtml(HTML_URL);
                        } catch (Exception e) {
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
























