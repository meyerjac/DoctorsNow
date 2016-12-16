package com.example.jacksonmeyer.sidekick.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jacksonmeyer.sidekick.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JacksonContact extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.phoneNumberTextView)
    TextView mPhoneNumberTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jackson_contact);
        ButterKnife.bind(this);
        mPhoneNumberTextView.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == mPhoneNumberTextView) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + mPhoneNumberTextView.getText().toString()));
            startActivity(phoneIntent);
        }
    }
}
