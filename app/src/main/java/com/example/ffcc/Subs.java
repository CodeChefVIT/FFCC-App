package com.example.ffcc;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Subs {
    public ArrayList<String> ab=new ArrayList<String>();
    public ArrayAdapter<String> adp;

    public String num;

    public ArrayAdapter<String> getAdp() {
        return adp;
    }

    public void setAdp(Context context) {
        this.adp=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,ab);
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Subs(ArrayList<String> ab, String num) {
        this.ab = ab;
        this.num=num;
    }

    public ArrayList<String> getAb() {
        return ab;
    }

    public void setAb(ArrayList<String> ab) {
        this.ab = ab;

    }
}
