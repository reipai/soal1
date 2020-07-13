package com.reivai.soal1.ui.home;

import com.google.gson.annotations.SerializedName;

public class HomeModel {

    @SerializedName("id")
    public int id;

    @SerializedName("image_url")
    public String img_url;

    public HomeModel(int id, String img_url) {
        this.id = id;
        this.img_url = img_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
