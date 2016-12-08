package com.example.jacksonmeyer.sidekick.ui;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jacksonmeyer.sidekick.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FormInfoActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.secondPageButton)
    Button mSecondPageButton;
    @Bind(R.id.emailInput)
    EditText mEmailInput;
    @Bind(R.id.passwordInput)
    EditText mPasswordInput;
    @Bind(R.id.nameInput)
    EditText mNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_info);
        ButterKnife.bind(this);
        mSecondPageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        {
            if (v == mSecondPageButton) {
                if ((mPasswordInput.getText().toString().equals("")) || (mEmailInput.getText().toString().equals("")) ||
                        (mNameInput.getText().toString().equals(""))) {
                    Toast.makeText(FormInfoActivity.this, "please fill out all the text fields!", Toast.LENGTH_SHORT).show();
                } else {
                    String password = mPasswordInput.getText().toString();
                    String email = mEmailInput.getText().toString();
                    String name = mNameInput.getText().toString();
                    Intent intent = new Intent(FormInfoActivity.this, DoctorsActivity.class);
                    intent.putExtra("password", password);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }
            };
        };
    };
}
