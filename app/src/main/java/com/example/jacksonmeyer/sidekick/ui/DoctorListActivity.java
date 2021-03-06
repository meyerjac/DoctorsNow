package com.example.jacksonmeyer.sidekick.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentQuery;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private DoctorListAdapter mAdapter;
    public ArrayList<Doctor> mDoctors = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        ButterKnife.bind(this);

        String mName;
        String mQuery;

        Intent intent = getIntent();
        mName = intent.getStringExtra("name");
        mQuery = intent.getStringExtra("query");

        getDoctors(mName, mQuery);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentQuery = mSharedPreferences.getString(Constants.PREFERENCES_QUERY_KEY, null);

        if (mRecentQuery != null) {
            getDoctors(mQuery, mName);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                Intent intent = getIntent();
                String name = intent.getStringExtra("name");

                addToSharedPreferences(query);
                getDoctors(query, name);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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

    private void addToSharedPreferences(String query) {
        mEditor.putString(Constants.PREFERENCES_QUERY_KEY, query).apply();
    }
}



