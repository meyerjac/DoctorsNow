package com.example.jacksonmeyer.sidekick.models;

import org.parceler.Parcel;

@Parcel
public class Doctor {
     String Name;
     String Image_url;
     String Bio;
     String Gender;
     String Address;
     String Website;

    public Doctor() {}

    public Doctor(String Name, String Image_url, String Bio, String Gender, String Address, String Website) {
        this.Name = Name;
        this.Image_url = Image_url;
        this.Bio = Bio;
        this.Gender = Gender;
        this.Address = Address;
        this.Website =Website;

    }

    public String getName() {
        return Name;
    }

    public String getImage_url() {
        return Image_url;
    }

    public String getBio() {
        return Bio;
    }

    public String getGender() {
        return Gender;
    }

    public String getAddress() {
        return Address;
    }

    public String getWebsite() {
        return Website;
    }

}
