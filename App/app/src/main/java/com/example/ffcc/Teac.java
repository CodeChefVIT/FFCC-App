package com.example.ffcc;

import java.util.ArrayList;

public class Teac {

    ArrayList<String> n;
    public String name;

    public Teac(ArrayList<String> n, String name) {
        this.n = n;
        this.name = name;
    }

    public ArrayList<String> getN() {
        return n;
    }

    public void setN(ArrayList<String> n) {
        this.n = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}