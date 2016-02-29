package com.turman.fb.httpclient;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.turman.fb.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by dqf on 2016/2/26.
 */
public class HttpClientActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_send;
    private WebView wView;
    private static final int SHOW_DATA = 0x123;
    private String detail = "";

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == SHOW_DATA) {
                wView.loadDataWithBaseURL("",detail,"text/html","UTF-8","");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpclient);
        btn_send = (Button) findViewById(R.id.get_page);
        wView = (WebView) findViewById(R.id.show_web);

        btn_send.setOnClickListener(this);
        wView.getSettings().setDomStorageEnabled(true);
    }

    @Override
    public void onClick(View v) {
        GetByHttpClient();
    }

    private void GetByHttpClient() {
        new Thread(){
            @Override
            public void run() {
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet("http://cst.9joint-eco.com/ec-web/com/login.action");
                try {
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == 200){
                        HttpEntity entity = httpResponse.getEntity();
                        detail = EntityUtils.toString(entity, "UTF-8");
                        handler.sendEmptyMessage(SHOW_DATA);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}








































































