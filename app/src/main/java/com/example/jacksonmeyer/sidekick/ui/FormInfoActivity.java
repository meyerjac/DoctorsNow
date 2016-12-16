package com.example.jacksonmeyer.sidekick.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jacksonmeyer.sidekick.Constants;
import com.example.jacksonmeyer.sidekick.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FormInfoActivity extends AppCompatActivity implements View.OnClickListener {

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
    private DatabaseReference mSearchedNameReference;
    private ValueEventListener mSearchedNameReferenceListener;

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

        mSearchedNameReferenceListener = mSearchedNameReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot nameSnapshot : dataSnapshot.getChildren()) {
                    String name = nameSnapshot.getValue().toString();
//                    Log.d("Names updated", "name: " + name);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_info);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();


        mSecondPageButton.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedNameReference.removeEventListener(mSearchedNameReferenceListener);
    }

    @Override
    public void onClick(View v) {
        if (v == mSecondPageButton) {
            String name = mNameInput.getText().toString();
            String query = mQueryInput.getText().toString();
//            addToSharedPreferences(query);
            saveNameToFirebase(name);


            if (name.equals("")) {
                mNameInput.setError("Please enter a name");
                return;
            }
            if (query.equals("")) {
                mQueryInput.setError("Keyword cannot be blank");
                return;
            }
            Intent intent = new Intent(FormInfoActivity.this, DoctorListActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("query", query);
            startActivity(intent);
        }
    }

    public void saveNameToFirebase(String name) {
        mSearchedNameReference.push().setValue(name);
    }
//    private void addToSharedPreferences(String query) {
//        mEditor.putString(Constants.PREFERENCES_QUERY_KEY, query).apply();
//    }
}

