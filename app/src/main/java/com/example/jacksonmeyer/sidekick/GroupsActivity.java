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

public class GroupsActivity extends AppCompatActivity {
    @Bind(R.id.zipView) TextView mZipView;
    @Bind(R.id.textView) ListView mListView;

    public ArrayList<Doctor> mDoctors = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String docName = intent.getStringExtra("doctorName");

        getGroups(docName);
    }

    private void getGroups(String doctor) {
        final BetterDoctorService betterDoctorService = new BetterDoctorService();
        betterDoctorService.findDoctor(doctor, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mDoctors = BetterDoctorService.processResults(response);

                GroupsActivity.this.runOnUiThread(new Runnable() {

                }
                    @Override
                    public void run() {
                        String[] doctorNames = new String[mDoctors.size()];
                        for (int i = 0; i< doctorNames.length; i++) {
                            doctorNames[i] = mDoctors.get(i).getFirstName();
                        }

                    ArrayAdapter adapter = new ArrayAdapter(GroupsActivity.this,
                            android.R.layout.simple_list_item_1, doctorNames);
                    mListView.setAdapter(adapter);
                    }
                }
            }
        });
    }

