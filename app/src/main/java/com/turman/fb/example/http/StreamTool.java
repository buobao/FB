package com.turman.fb.example.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dqf on 2016/2/26.
 */
public class StreamTool {
    public static byte[] read(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer))!=-1){
            outputStream.write(buffer,0,len);
        }
        inputStream.close();
        return outputStream.toByteArray();
    }

}
