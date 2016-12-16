package com.example.jacksonmeyer.sidekick.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jacksonmeyer.sidekick.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.jacksonmeyer.sidekick.R.id.findDoctorsButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(findDoctorsButton)
    Button mFirstContinueButton;
    @Bind(R.id.textView3)
    TextView mTextView3;
    @Bind(R.id.savedDoctorsButton)
    Button mSavedDoctorsButton;



    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFirstContinueButton.setOnClickListener(this);
        mSavedDoctorsButton.setOnClickListener(this);



        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Thanks for visiting " + user.getDisplayName() + "!");
                } else {

                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        if (v == mFirstContinueButton) {
            Intent intent = new Intent(MainActivity.this, FormInfoActivity.class);
            startActivity(intent);
            Toast.makeText(MainActivity.this, "Thanks for joining the team!", Toast.LENGTH_SHORT).show();
        }  if (v == mSavedDoctorsButton) {
            Toast.makeText(MainActivity.this, "saved doctor list here", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, SavedDoctorListActivity.class);
            startActivity(intent);
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        if (id == R.id.contactMe) {
            navigateToContactPage();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void navigateToContactPage() {
        Intent intent = new Intent(MainActivity.this, JacksonContact.class);
        startActivity(intent);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}

