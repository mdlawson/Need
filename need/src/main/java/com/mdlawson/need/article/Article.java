package com.mdlawson.need.article;

import android.os.Parcel;
import android.os.Parcelable;

public class Article {

    String id;
    String title;

    public Article() {

    }


    public void getDetails() {

    }

    public Article id(String id) {
        this.id = id;
        return this;
    }

    public Article title(String title) {
        this.title = title;
        return this;
    }


    @Override
    public String toString() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
