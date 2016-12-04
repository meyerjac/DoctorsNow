package com.example.jacksonmeyer.sidekick;

import java.util.ArrayList;

public class Doctor {
    private String mFirstName;
    private String mLastName;
    private String mImage_url;
    private String mBio;
    private String mGender;
    private String mAddress;

    public Doctor(String mFirstName, String mLastName, String mImage_url, String mBio, String mGender, String mAddress) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mImage_url = mImage_url;
        this.mBio = mBio;
        this.mGender = mGender;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
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
