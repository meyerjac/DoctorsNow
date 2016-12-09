package com.example.jacksonmeyer.sidekick.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jacksonmeyer.sidekick.Constants;
import com.example.jacksonmeyer.sidekick.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FormInfoActivity extends AppCompatActivity implements View.OnClickListener {


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedNameReference;

    @Bind(R.id.secondPageButton)
    Button mSecondPageButton;
    @Bind(R.id.nameInput)
    EditText mNameInput;
    @Bind(R.id.queryInput)
    EditText mQueryInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedNameReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_NAME);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_info);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();


        mSecondPageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSecondPageButton) {
            String name = mNameInput.getText().toString();
            String query = mQueryInput.getText().toString();
            addToSharedPreferences(query);
            saveNameToFirebase(name);


            if (name.equals("")) {
                mNameInput.setError("Please enter a name");
                return;
            }
            if (query.equals("")) {
                mQueryInput.setError("Keyword cannot be blank");
                return;
            }

            Intent intent = new Intent(FormInfoActivity.this, DoctorsActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("query", query);
            startActivity(intent);
        }
    }

    public void saveNameToFirebase(String name) {
        mSearchedNameReference.push().setValue(name);
    }

    private void addToSharedPreferences(String query) {
        mEditor.putString(Constants.PREFERENCES_QUERY_KEY, query).apply();
    }
}

