package com.example.jacksonmeyer.sidekick;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jacksonmeyer.sidekick.adapters.DoctorListAdapter;
import com.example.jacksonmeyer.sidekick.models.Doctor;
import com.example.jacksonmeyer.sidekick.services.DoctorService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DoctorsActivity extends AppCompatActivity {
    @Bind(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    private DoctorListAdapter mAdapter;

    public ArrayList<Doctor> mDoctors = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

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
                       mAdapter =  new DoctorListAdapter(getApplicationContext(), mDoctors);
                        mRecyclerView.setAdapter( mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(DoctorsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        }
                    });
                }
            });
        }
    }



