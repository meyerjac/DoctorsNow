package com.example.jacksonmeyer.sidekick.ui;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jacksonmeyer.sidekick.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FormInfoActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.secondPageButton)
    Button mSecondPageButton;
    @Bind(R.id.nameInput)
    EditText mNameInput;
    @Bind(R.id.queryInput)
    EditText mQueryInput;




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
                String name = mNameInput.getText().toString();
                String query = mQueryInput.getText().toString();
                Intent intent = new Intent(FormInfoActivity.this, DoctorsActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("query", query);
                startActivity(intent);
            }
        };
    }
}

