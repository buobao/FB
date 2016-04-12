package com.turman.fb.example.newhttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/9.
 */
public class NatureHttp {

    public static String sendGet(String url, Map<String,String> params) {

        String result = "";
        BufferedReader in = null;

        if (url == null  || "".equals(url)){
            return null;
        }

        if (params.size() > 0) {
            url += "?";
            for (String s : params.keySet()){
                url += s+"="+params.get(s) + "&";
            }
            url = url.substring(0,url.length()-1);
        }

        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();

            connection.setRequestProperty("accept","*/*");
            connection.setRequestProperty("connection","Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();

            Map<String, List<String>> map = connection.getHeaderFields();

            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

}






























