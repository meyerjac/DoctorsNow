package com.example.jacksonmeyer.sidekick.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jacksonmeyer.sidekick.R;
import com.google.firebase.auth.FirebaseAuth;

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
        if (id == R.id.test1) {
            logout();
            return true;
        }
        if (id == R.id.test2) {
            logout();
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
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}

