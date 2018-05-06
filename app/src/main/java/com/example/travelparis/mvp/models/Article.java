package com.example.travelparis.mvp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Andrei on 05.05.2018.
 */

public class Article implements Parcelable {

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    private int imageResource;
    private String title;
    private String text;


    public Article(int imageResource, String title, String text) {
        this.imageResource = imageResource;
        this.title = title;
        this.text = text;
    }


    private Article(Parcel in) {
        imageResource = in.readInt();
        title = in.readString();
        text = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imageResource);
        parcel.writeString(title);
        parcel.writeString(text);
    }
}
