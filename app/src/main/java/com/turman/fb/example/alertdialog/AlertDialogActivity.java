package com.turman.fb.example.alertdialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.turman.fb.R;

/**
 * Created by dqf on 2016/2/17.
 */
public class AlertDialogActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_dialog_one;
    private Button btn_dialog_two;
    private Button btn_dialog_three;
    private Button btn_dialog_four;

    private Context mContext;
    private boolean[] checkItems;

    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertdialog);
        mContext = AlertDialogActivity.this;
        bindView();
    }

    private void bindView() {
        btn_dialog_one = (Button) findViewById(R.id.btn_dialog_one);
        btn_dialog_two = (Button) findViewById(R.id.btn_dialog_two);
        btn_dialog_three = (Button) findViewById(R.id.btn_dialog_three);
        btn_dialog_four = (Button) findViewById(R.id.btn_dialog_four);

        btn_dialog_one.setOnClickListener(this);
        btn_dialog_two.setOnClickListener(this);
        btn_dialog_three.setOnClickListener(this);
        btn_dialog_four.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog_one:
                alert = null;
                builder = new AlertDialog.Builder(mContext);
                alert = builder.setIcon(R.drawable.icon)
                        .setTitle("系统提示:")
                        .setMessage("This is a normal alert dialog!")
                        .setNegativeButton("取消",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext, "你点击了取消按钮~",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext, "你点击了确定按钮~",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("中立",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext,"你点击了中立按钮~",Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                alert.show();
                break;
            case R.id.btn_dialog_two:
                final String[] lesson = new String[] {"语文","数学","英语","政治","历史","地理","化学","物理","生物"};
                alert = null;

                builder = new AlertDialog.Builder(mContext);
                alert = builder.setIcon(R.drawable.obama)
                        .setTitle("选择你喜欢的课程")
                        .setItems(lesson, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext,"你选择了"+lesson[which],Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                alert.show();
                break;
            case R.id.btn_dialog_three:
                final String[] fruits = new String[]{"苹果","香蕉","提子","龙眼","枣"};
                alert = null;
                builder = new AlertDialog.Builder(mContext);
                alert = builder.setIcon(R.drawable.world)
                        .setTitle("选择你喜欢的水果")
                        .setSingleChoiceItems(fruits, 0, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "你选择了 "+fruits[which],Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                alert.show();
                break;
            case R.id.btn_dialog_four:
                final String[] songs = new String[]{"A","B","C","D","E","F"};
                checkItems = new boolean[]{false,false,false,false,false,false};
                alert = null;
                builder = new AlertDialog.Builder(mContext);
                alert = builder.setIcon(R.mipmap.ic_launcher)
                        .setMultiChoiceItems(songs,checkItems,new OnMultiChoiceClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                checkItems[which] = isChecked;
                            }
                        })
                        .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String result = "";
                                for (int i=0;i<checkItems.length;i++) {
                                    if (checkItems[i]) {
                                        result += songs[i]+",";
                                    }
                                }
                                Toast.makeText(getApplicationContext(),"你选择了 "+result,Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                alert.show();
                break;
        }
    }
}

































