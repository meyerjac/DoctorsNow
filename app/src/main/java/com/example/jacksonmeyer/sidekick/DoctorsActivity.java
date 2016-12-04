package com.example.jacksonmeyer.sidekick;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DoctorsActivity extends AppCompatActivity {
    @Bind(R.id.doctorNameTextView)
    TextView mDoctorNameTextView;
    @Bind(R.id.listView)
    ListView mListView;
    public static final String TAG = "doctors activity";

    public ArrayList<Doctor> mDoctors = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        mDoctorNameTextView.setText("Here are all the Doctors Named: " + name);

        getDoctors(name);
    }

    private void getDoctors(String name) {
        final DoctorService doctorService= new DoctorService();
        doctorService.findDoctors(name, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mDoctors = doctorService.processResults(response);


                DoctorsActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        String[] doctorNames = new String[mDoctors.size()];
                        for (int i = 0; i < doctorNames.length; i++) {
                            doctorNames[i] = mDoctors.get(i).getFirstName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(DoctorsActivity.this, android.R.layout.simple_list_item_1, doctorNames);
                        mListView.setAdapter(adapter);

                        for (Doctor doctor : mDoctors) {
                            Log.d(TAG, "Name: " + doctor.getFirstName());
                            Log.d(TAG, "Phone: " + doctor.getLastName());
                            Log.d(TAG, "Website: " + doctor.getBio());
                            Log.d(TAG, "Image url: " + doctor.getImage_url());
                            Log.d(TAG, "gender: " + doctor.getGender());
                            Log.d(TAG, "Address: " + doctor.getAddress());

                        }
                    }
                });
            }
        });
    }

}


