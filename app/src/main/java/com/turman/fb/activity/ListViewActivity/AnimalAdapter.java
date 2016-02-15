package com.turman.fb.activity.ListViewActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.turman.fb.R;

import java.util.LinkedList;

/**
 * Created by dqf on 2016/2/14.
 */
public class AnimalAdapter extends BaseAdapter {

    private LinkedList<Animal> mData;
    private Context mContext;

    public AnimalAdapter(LinkedList<Animal> mData,Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_animal, parent, false);

            holder = new ViewHolder();
            holder.img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
            holder.txt_aName = (TextView) convertView.findViewById(R.id.item_name);
            holder.txt_aSpeak = (TextView) convertView.findViewById(R.id.item_speak);
            convertView.setTag(holder);   //将Holder存储到convertView中
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        ImageView img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
//        TextView name = (TextView) convertView.findViewById(R.id.item_name);
//        TextView speak = (TextView) convertView.findViewById(R.id.item_speak);
//
//        img_icon.setBackgroundResource(mData.get(position).getIcon());
//        name.setText(mData.get(position).getName());
//        speak.setText(mData.get(position).getSpeak());

        holder.img_icon.setBackgroundResource(mData.get(position).getIcon());
        holder.txt_aName.setText(mData.get(position).getName());
        holder.txt_aSpeak.setText(mData.get(position).getSpeak());
        return convertView;
    }

    static class ViewHolder{
        ImageView img_icon;
        TextView txt_aName;
        TextView txt_aSpeak;
    }
}
