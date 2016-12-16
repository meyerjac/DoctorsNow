package com.example.jacksonmeyer.sidekick.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.jacksonmeyer.sidekick.Constants;
import com.example.jacksonmeyer.sidekick.R;
import com.example.jacksonmeyer.sidekick.adapters.FirebaseDoctorViewHolder;
import com.example.jacksonmeyer.sidekick.models.Doctor;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedDoctorListActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    private DatabaseReference mDoctorReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        ButterKnife.bind(this);

        mDoctorReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SAVED_DOCTORS);
        setUpFirebaseAdapter();
        Log.d(TAG, "onCreate: " + mDoctorReference);
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Doctor, FirebaseDoctorViewHolder>
                (Doctor.class, R.layout.doctor_list_item, FirebaseDoctorViewHolder.class,
                        mDoctorReference) {

            @Override
            protected void populateViewHolder(FirebaseDoctorViewHolder viewHolder,
                                              Doctor model, int position) {
                viewHolder.bindDoctor(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}

