package com.example.jacksonmeyer.sidekick;

import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;


public class MeetupService {

    public static void findGroups(String name, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();


        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MEETUP_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.API_QUERY_PARAMETER, Constants.APIKEY);
        urlBuilder.addQueryParameter(Constants.NAME_QUERY_PARAMETER, name);
        String url = urlBuilder.build().toString();
        Log.d("log", "url: " + url);

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}

