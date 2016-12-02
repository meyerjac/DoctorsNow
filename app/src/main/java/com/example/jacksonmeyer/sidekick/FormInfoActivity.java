package com.example.jacksonmeyer.sidekick;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FormInfoActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.secondPageButton)
    Button mSecondPageButton;
    @Bind(R.id.nameInput)
    EditText mNameInput;
    @Bind(R.id.emailInput)
    EditText mEmailInput;
    @Bind(R.id.passwordInput)
    EditText mPasswordInput;
    @Bind(R.id.ageInput)
    EditText mAgeInput;
    @Bind(R.id.zipInput)
    EditText mZipInput;

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
                if ((mPasswordInput.getText().toString().equals("")) || (mAgeInput.getText().toString().equals("")) ||
                        (mNameInput.getText().toString().equals("")) || (mEmailInput.getText().toString().equals("")) ||
                        (mZipInput.getText().toString().equals(""))) {
                    Toast.makeText(FormInfoActivity.this, "please fill out all the text fields!", Toast.LENGTH_SHORT).show();
                } else {
                    String password = mPasswordInput.getText().toString();
                    String age = mAgeInput.getText().toString();
                    String name = mNameInput.getText().toString();
                    String email = mEmailInput.getText().toString();
                    String zip = mZipInput.getText().toString();
                    Intent intent = new Intent(FormInfoActivity.this, GroupsActivity.class);
                    intent.putExtra("password", password);
                    intent.putExtra("age", age);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("zip", zip);
                    startActivity(intent);
                }
            }
            ;
        }
        ;
    };
}
