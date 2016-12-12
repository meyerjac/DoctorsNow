package com.example.jacksonmeyer.sidekick.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jacksonmeyer.sidekick.Constants;
import com.example.jacksonmeyer.sidekick.R;
import com.example.jacksonmeyer.sidekick.models.Doctor;
import com.example.jacksonmeyer.sidekick.ui.DoctorDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import static com.example.jacksonmeyer.sidekick.R.id.genderTextView;

public class FirebaseDoctorViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {
        private static final int MAX_WIDTH = 200;
        private static final int MAX_HEIGHT = 200;

        View mView;
        Context mContext;

        public FirebaseDoctorViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindDoctor(Doctor doctor) {
        ImageView doctorImageView = (ImageView) mView.findViewById(R.id.doctorImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.doctorNameTextView);
        TextView bioTextView = (TextView) mView.findViewById(R.id.bioTextView);
        TextView ratingTextView = (TextView) mView.findViewById(genderTextView);

        Picasso.with(mContext)
                .load(doctor.getImage_url())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(doctorImageView);

        nameTextView.setText(doctor.getName());
        bioTextView.setText(doctor.getGender());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Doctor> doctors = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SAVED_DOCTORS);
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

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
