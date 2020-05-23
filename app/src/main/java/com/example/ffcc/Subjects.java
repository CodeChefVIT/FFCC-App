package com.example.ffcc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subjects {
    @SerializedName("CODE")
    @Expose
    String code;
    @SerializedName("TITLE")
    @Expose
    String title;
    @SerializedName("CREDITS")
    @Expose
    int credits;

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
