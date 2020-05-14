package com.example.ffcc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Teachers {
    @SerializedName("OWNER")
    @Expose
    private String owner;
    @SerializedName("CODE")
    @Expose
    private String code;
    @SerializedName("TITLE")
    @Expose
    private String title;
    @SerializedName("TYPE")
    @Expose
    private String type;
    @SerializedName("CREDITS")
    @Expose
    private String credits;
    @SerializedName("ROOM")
    @Expose
    private String room;
    @SerializedName("SLOT")
    @Expose
    private String slot;
    @SerializedName("FACULTY")
    @Expose
    private String faculty;
    @SerializedName("FLAG")
    @Expose
    private String flag;
    @SerializedName("REVIEW")
    @Expose
    private String review;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
