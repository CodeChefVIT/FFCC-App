package com.example.ffcc;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Subs {

    public String code;
    public String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subs( String code,String name) {
        this.code=code;
        this.name=name;
    }


}
