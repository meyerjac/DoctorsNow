package com.example.jacksonmeyer.sidekick;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    public static final String TAG = DoctorsActivity.class.getSimpleName();

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
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        Log.v("hello", jsonData);
                        mDoctors = doctorService.processResults(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        });
    }

}


