package com.example.jacksonmeyer.sidekick;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.firstContinueButton)
    Button mFirstContinueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFirstContinueButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == mFirstContinueButton) {
            Intent intent = new Intent(MainActivity.this, FormInfoActivity.class);
            startActivity(intent);
            Toast.makeText(MainActivity.this, "Thanks for joining the team!", Toast.LENGTH_SHORT).show();

        }
    }
}

