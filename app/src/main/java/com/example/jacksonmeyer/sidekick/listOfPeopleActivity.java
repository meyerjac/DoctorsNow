package com.example.jacksonmeyer.sidekick;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class listOfPeopleActivity extends AppCompatActivity {
    @Bind(R.id.nameTextView) TextView mnameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_people);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        mnameTextView.setText("your name: " + name);
    }
}