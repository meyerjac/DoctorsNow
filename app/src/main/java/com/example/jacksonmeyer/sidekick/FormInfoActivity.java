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

public class FormInfoActivity extends AppCompatActivity {

    @Bind(R.id.secondPageButton) Button msecondPageButton;
    @Bind(R.id.nameInput) EditText mnameInput;
    @Bind(R.id.emailInput) EditText memailInput;
    @Bind(R.id.passwordInput) EditText mpasswordInput;
    @Bind(R.id.ageInput) EditText mageInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_info);
        ButterKnife.bind(this);


        msecondPageButton.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                if ((mpasswordInput.getText().toString() == "") || (mageInput.getText().toString()=="") ||
                        (mnameInput.getText().toString()=="") || (memailInput.getText().toString()=="")) {
                    Toast.makeText(FormInfoActivity.this, "please fill out all the text fields!", Toast.LENGTH_SHORT).show();
                } else {
                    String password = mpasswordInput.getText().toString();
                    String age = mageInput.getText().toString();
                    String name = mnameInput.getText().toString();
                    String email = memailInput.getText().toString();
                    Intent intent = new Intent(FormInfoActivity.this, listOfPeopleActivity.class);
                    intent.putExtra("password", password);
                    intent.putExtra("age", age);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }
            };
        });
    };
}