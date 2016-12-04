package com.example.jacksonmeyer.sidekick;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

    public ArrayList<Doctor> processResults(Response response) {
        ArrayList<Doctor> doctors = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject betterDoctorJSON = new JSONObject(jsonData);
                JSONArray doctorsJSON = betterDoctorJSON.getJSONArray("data");
                for (int i = 0; i < doctorsJSON.length(); i++) {
                    JSONObject doctor = doctorsJSON.getJSONObject(i);
                    JSONArray practices = doctor.getJSONArray("practices");
                    JSONObject profile = doctor.getJSONObject("profile");
                    String firstName = profile.getString("first_name");
                    String LastName = profile.getString("first_name");
                    String firstName = profile.getString("first_name");

                    Doctor doctorConstructor = new Doctor(firstName, lastName, imageUrl, bio, );
                    doctors.add(doctorConstructor);
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
