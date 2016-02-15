package com.turman.fb.activity.ListViewActivity;

/**
 * Created by dqf on 2016/2/14.
 */
public class Animal {
    private String name;
    private String speak;
    private int icon;

    public Animal(){}
    public Animal(String name, String speak,int icon) {
        this.name = name;
        this.speak = speak;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeak() {
        return speak;
    }

    public void setSpeak(String speak) {
        this.speak = speak;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
