package com.example.jacksonmeyer.sidekick;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;


public class listOfPeopleActivity extends AppCompatActivity {
    @Bind(R.id.nameTextView) TextView mNameTextView;
    @Bind(R.id.namesListView) ListView mNamesListView;
    private String[] peopleList = new String[] {"Elon Musk", "Mother Teresa",
            "Abe Lincoln", "Sophie LaCoste", "Brian Tesky", "Jess Bezos",
            "Aaron Box", "Jackson Meyer", "Juneau Meyer",
            "Megan Keough", "Amazing people", "Jesus",
            "JB Straubel", "Dan keough", "Elon Musk", "Mother Teresa",
            "Abe Lincoln", "Sophie LaCoste", "Brian Tesky", "Jess Bezos",
            "Aaron Box", "Jackson Meyer", "Juneau Meyer",
            "Megan Keough", "Amazing people", "Jesus",
            "JB Straubel", "Dan keough"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_people);
        ButterKnife.bind(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, peopleList);
        mNamesListView.setAdapter(adapter);
        mNamesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String people = ((TextView)view).getText().toString();
                Toast.makeText(listOfPeopleActivity.this, people, Toast.LENGTH_LONG).show();
            }
        });
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        String email = intent.getStringExtra("email");
        mNameTextView.setText("Hey " + name + "with your contact email address of: " + email +  ". " + "you are " + age + " years old " + " Here are the other people that are near you!");
    }
}