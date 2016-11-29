package com.example.jacksonmeyer.sidekick;

import android.content.Intent;
import android.hardware.camera2.params.Face;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FacebookLogin extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.loginButton)
    Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);
        ButterKnife.bind(this);
        Toast.makeText(FacebookLogin.this, "This isn't connected to facebook or Firebase yet, " +
                "just click the button", Toast.LENGTH_LONG).show();

        mLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mLoginButton) {
            Intent intent = new Intent(FacebookLogin.this, MainActivity.class);
            startActivity(intent);
        }
    }

    }


