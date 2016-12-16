package com.example.jacksonmeyer.sidekick.services;

import com.example.jacksonmeyer.sidekick.Constants;
import com.example.jacksonmeyer.sidekick.models.Doctor;

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

public class DoctorService {

    private static final String TAG = "error";

    public static void findDoctors(String name, String query, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BETTER_DOCTOR_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.BETTER_DOCTOR_NAME_QUERY_PARAMETER, name);
        urlBuilder.addQueryParameter(Constants.BETTER_DOCTOR_QUERY_PARAMETER, query);
        urlBuilder.addQueryParameter(Constants.API_QUERY_PARAMETER, Constants.APIKEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Doctor> processResults(Response response) {
        ArrayList<Doctor> doctors = new ArrayList<>();
        try {
            if (response.isSuccessful()) {
                String jsonData = response.body().string();
                JSONObject results = new JSONObject(jsonData);
                JSONArray doctorsResults = results.getJSONArray("data");
                for (int i = 0; i < doctorsResults.length(); i++) {
                    JSONObject doctor = doctorsResults.getJSONObject(i);
                    JSONArray practices = doctor.getJSONArray("practices");
                    for (int j = 0; j < 1; j++) {
                        JSONObject aPractice = practices.getJSONObject(j);
                        JSONObject addressJSON = practices.getJSONObject(j).getJSONObject("visit_address");
                        String address = "";
                        String city = addressJSON.getString("city");
                        String state = addressJSON.getString("state");
                        String street = addressJSON.getString("street");
                        String zip = addressJSON.getString("zip");
                        String Address = address + street + "\n" + city + ", " + state + " " + zip;

                    JSONObject profile = doctor.getJSONObject("profile");
                    String firstName = profile.getString("first_name");
                    String lastName = profile.getString("last_name");
                    String imageUrl = profile.getString("image_url");

                        //trying to get null profile pictures to display default picture
//                        if (imageUrl.equals(null)) {
//                            imageUrl = ("http://www.vectorea.com/tvx_uploads/4/661-medical-icons.jpg").toString();
//                        }
                    String bio = profile.optString("bio");
                    String gender = profile.optString("gender");
                        String website = aPractice.optString("doctors_pagination_url");
                        String Name = firstName + " " + lastName;

                    Doctor doctorConstructor = new Doctor(Name, imageUrl, bio, gender, Address, website);
                    doctors.add(doctorConstructor);
                    }
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
