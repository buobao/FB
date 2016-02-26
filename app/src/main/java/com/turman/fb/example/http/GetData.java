package com.turman.fb.example.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dqf on 2016/2/26.
 */
public class GetData {

    //定义一个网络获取图片的方法
    public static byte[] getImage(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() != 200){
            throw  new RuntimeException("请求url失败");
        }
        InputStream inputStream = conn.getInputStream();
        byte[] bt = StreamTool.read(inputStream);
        inputStream.close();
        return bt;
    }

    //获取网页的html源代码
    public static String getHtml(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200){
            InputStream in = conn.getInputStream();
            byte[] data = StreamTool.read(in);
            String html = new String(data,"UTF-8");
            return html;
        }
        return null;
    }
}





























