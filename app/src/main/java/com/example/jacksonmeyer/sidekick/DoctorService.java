package com.example.jacksonmeyer.sidekick;

import okhttp3.Callback;
import okhttp3.OkHttpClient;

public class DoctorService {

    public static void findDoctors(String name, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder().build();

    }
}
