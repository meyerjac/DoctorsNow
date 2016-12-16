package com.example.jacksonmeyer.sidekick.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jacksonmeyer.sidekick.Constants;
import com.example.jacksonmeyer.sidekick.R;
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

public class DoctorListActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private DoctorListAdapter mAdapter;
    private String mName;
    private String mQuery;
    public ArrayList<Doctor> mDoctors = new ArrayList<>();
    private SharedPreferences mSharedPreferences;
    private String mRecentQuery;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String mName = intent.getStringExtra("name");
        String mQuery = intent.getStringExtra("query");
        getDoctors(mName, mQuery);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentQuery = mSharedPreferences.getString(Constants.PREFERENCES_QUERY_KEY, null);
        if (mRecentQuery != null) {
            getDoctors(mQuery, mName);
        }

    }


    private void getDoctors(String name, String query) {
        final DoctorService doctorService= new DoctorService();
        doctorService.findDoctors(name, query, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mDoctors = doctorService.processResults(response);
                DoctorListActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                       mAdapter =  new DoctorListAdapter(getApplicationContext(), mDoctors);
                        mRecyclerView.setAdapter( mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(DoctorListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        }
                    });
                }
            });
        }
    }



