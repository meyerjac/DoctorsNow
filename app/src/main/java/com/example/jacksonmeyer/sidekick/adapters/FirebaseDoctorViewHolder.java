package com.example.jacksonmeyer.sidekick.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jacksonmeyer.sidekick.Constants;
import com.example.jacksonmeyer.sidekick.R;
import com.example.jacksonmeyer.sidekick.models.Doctor;
import com.example.jacksonmeyer.sidekick.ui.DoctorDetailActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;

import static com.example.jacksonmeyer.sidekick.R.id.doctorImageView;

public class FirebaseDoctorViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    public static final String TAG = "TAG";
        private static final int MAX_WIDTH = 200;
        private static final int MAX_HEIGHT = 200;
        View mView;
        Context mContext;
        final ArrayList<Doctor> doctor = new ArrayList<>();

        public FirebaseDoctorViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public ArrayList<Doctor> getDoctor() {
        return doctor;
    }

    public void bindDoctor(Doctor doctor) {
        Log.d(TAG, "bindDoctor: " + doctor);
        TextView nameTextView = (TextView) mView.findViewById(R.id.doctorName);
        TextView bioTextView = (TextView) mView.findViewById(R.id.bio);
        TextView genderTextView = (TextView) mView.findViewById(R.id.gender);
        ImageView DoctorImageView = (ImageView) mView.findViewById(doctorImageView);

        Log.d(TAG, "doctorname" + doctor.getName());
        nameTextView.setText(doctor.getName());
        bioTextView.setText(doctor.getBio());
        genderTextView.setText(doctor.getGender());
        Picasso.with(mContext)
                .load(doctor.getImage_url())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .into(DoctorImageView);

        Log.d(TAG, "bindDoctor: " + doctor);
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Doctor> doctors = new ArrayList<>();
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SAVED_DOCTORS).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    doctors.add(snapshot.getValue(Doctor.class));

                }

                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, DoctorDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("doctors", Parcels.wrap(doctors));
                Log.d(TAG, "onDataChange: " + doctors);
                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
