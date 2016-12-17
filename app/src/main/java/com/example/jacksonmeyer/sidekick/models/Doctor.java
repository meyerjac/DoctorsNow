package com.example.jacksonmeyer.sidekick.models;


import org.parceler.Parcel;

@Parcel
public class Doctor {
    String name;
    String image_url;
    String bio;
    String gender;
    String address;
    String website;

    public Doctor() {
    }

    public Doctor(String Name, String Image_url, String Bio, String Gender, String Address, String Website) {
        this.name = Name;
        this.image_url = Image_url;
        this.bio = Bio;
        this.gender = Gender;
        this.address = Address;
        this.website = Website;

    }

    public String getName() {
        return name;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getBio() {
        return bio;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getWebsite() {
        return website;
    }
}

