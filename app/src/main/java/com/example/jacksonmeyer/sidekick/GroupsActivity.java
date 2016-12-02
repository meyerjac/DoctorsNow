package com.example.jacksonmeyer.sidekick;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOError;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GroupsActivity extends AppCompatActivity {
    @Bind(R.id.zipView)
    TextView mZipView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String doctorNameGot = intent.getStringExtra("doctorName");
        mZipView.setText(doctorNameGot);

        getGroups(doctorNameGot);
    }

    private void getGroups(String zip) {
        final MeetupService meetupService = new MeetupService();
        meetupService.findGroups(zip, new Callback() {

            @Override
            public void onFailure (Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v("log", jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
