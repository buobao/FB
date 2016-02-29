package com.turman.fb.xml;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.turman.fb.R;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by dqf on 2016/2/26.
 */
public class XmlActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);
        Button btn = (Button) findViewById(R.id.get_data);
    }

    private ArrayList<Person> readxmlForSAX() throws Exception {
        //获取文件资源建立输入流对象
        InputStream is = getAssets().open("person1.xml");
        //①创建XML解析处理器
        SaxHelper ss = new SaxHelper();
        //②得到SAX解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //③创建SAX解析器
        SAXParser parser = factory.newSAXParser();
        //④将xml解析处理器分配给解析器,对文档进行解析,将事件发送给处理器
        parser.parse(is, ss);
        is.close();
        return ss.getPersons();
    }
}
