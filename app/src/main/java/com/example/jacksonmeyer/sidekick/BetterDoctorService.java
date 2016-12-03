package com.example.jacksonmeyer.sidekick;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;


public class BetterDoctorService {

    public static void findDoctor(String name, Callback callback) {
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
    public ArrayList<Doctor> processResults(Response response) {
        ArrayList<Doctor> doctors = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject doctorJSON = new JSONObject(jsonData);
                JSONArray DataJSON = doctorJSON.getJSONArray("data");
                for (int i = 0; i < DataJSON.length(); i++) {
                    JSONObject DoctorJSON = DataJSON.getJSONObject(i);
                    String firstname = DoctorJSON.getString("first_name");
                    String lastname = DoctorJSON.getString("last_name");
                    String bio = DoctorJSON.getString("bio");
                    String imageUrl = DoctorJSON.getString("image_url");
                    String gender = DoctorJSON.getString("gender");

//                    ArrayList<String> address = new ArrayList<>();
//                    JSONArray addressJSON = DoctorJSON.getJSONObject("location")
//                            .getJSONArray("display_address");
//                    for (int y = 0; y < addressJSON.length(); y++) {
//                        address.add(addressJSON.get(y).toString());
//                    }
//
//                    ArrayList<String> categories = new ArrayList<>();
//                    JSONArray categoriesJSON = DoctorJSON.getJSONArray("categories");
//
//                    for (int y = 0; y < categoriesJSON.length(); y++) {
//                        categories.add(categoriesJSON.getJSONArray(y).get(0).toString());
//                    }
//                    Doctor restaurant = new Doctor(firstname, lastname, bio, imageUrl,
//                            );
//                    restaurants.add(restaurant);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return doctors;
    }

}

