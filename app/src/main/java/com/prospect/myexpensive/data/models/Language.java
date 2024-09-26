package com.prospect.myexpensive.data.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Language implements Parcelable {
    private String id;
    private String name;

    // Constructor
    public Language(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Language() {

    }

    // Parcelable implementation
    protected Language(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    public static final Creator<Language> CREATOR = new Creator<Language>() {
        @Override
        public Language createFromParcel(Parcel in) {
            return new Language(in);
        }

        @Override
        public Language[] newArray(int size) {
            return new Language[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}