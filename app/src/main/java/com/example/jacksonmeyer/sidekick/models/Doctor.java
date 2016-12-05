package com.example.jacksonmeyer.sidekick.models;

public class Doctor {
    private String mName;
    private String mImage_url;
    private String mBio;
    private String mGender;
    private String mAddress;

    public Doctor(String mName, String mImage_url, String mBio, String mGender, String mAddress) {
        this.mName = mName;
        this.mImage_url = mImage_url;
        this.mBio = mBio;
        this.mGender = mGender;
        this.mAddress = mAddress;
    }

    public String getName() {
        return mName;
    }

    public String getImage_url() {
        return mImage_url;
    }

    public String getBio() {
        return mBio;
    }

    public String getGender() {
        return mGender;
    }

    public String getAddress() {
        return mAddress;
    }

}
