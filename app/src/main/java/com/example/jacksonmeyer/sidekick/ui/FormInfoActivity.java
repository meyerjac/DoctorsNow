package com.example.jacksonmeyer.sidekick.ui;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.EditText;
import com.example.jacksonmeyer.sidekick.Constants;
import com.example.jacksonmeyer.sidekick.R;
import com.example.jacksonmeyer.sidekick.util.Android_Gesture_Detector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FormInfoActivity extends AppCompatActivity implements SensorEventListener {
    private GestureDetector mGestureDetector;
    private static final String TAG = "swipe";
    @Bind(R.id.nameInput)
    EditText mNameInput;
    @Bind(R.id.queryInput)
    EditText mQueryInput;

    private DatabaseReference mSearchedNameReference;
    private ValueEventListener mSearchedNameReferenceListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchedNameReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_NAME);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_info);
        ButterKnife.bind(this);

        mSearchedNameReferenceListener = mSearchedNameReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot nameSnapshot : dataSnapshot.getChildren()) {
                    String name = nameSnapshot.getValue().toString();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        Android_Gesture_Detector custom_gesture_detector = new Android_Gesture_Detector() {
            @Override
            public void onSwipeUp() {
                Log.d(TAG, "onSwipeUp");
                String name = mNameInput.getText().toString();
                String query = mQueryInput.getText().toString();
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

            public void onSwipeDown() {
                Log.d(TAG, "onSwipedown");
            }

            public void onSwipeRight() {
                Log.d(TAG, "onSwiperight");
            }

            public void onSwipeLeft() {
                Log.d(TAG, "onSwipeleft");
            }
        };
        mGestureDetector = new GestureDetector(this, custom_gesture_detector);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedNameReference.removeEventListener(mSearchedNameReferenceListener);
    }

    public void saveNameToFirebase(String name) {
        mSearchedNameReference.push().setValue(name);
    }
}

