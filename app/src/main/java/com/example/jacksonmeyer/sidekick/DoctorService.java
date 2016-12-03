package com.example.jacksonmeyer.sidekick;

import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class DoctorService {

    public static void findDoctors(String name, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BETTER_DOCTOR_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.BETTER_DOCTOR_NAME_QUERY_PARAMETER, name);
        urlBuilder.addQueryParameter(Constants.API_QUERY_PARAMETER, Constants.APIKEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Log.d("url", url);
        Call call = client.newCall(request);
        call.enqueue(callback);

    }
}
