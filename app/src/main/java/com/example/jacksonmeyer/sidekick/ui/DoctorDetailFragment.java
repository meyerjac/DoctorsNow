package com.example.jacksonmeyer.sidekick.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jacksonmeyer.sidekick.Constants;
import com.example.jacksonmeyer.sidekick.R;
import com.example.jacksonmeyer.sidekick.models.Doctor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DoctorDetailFragment extends Fragment implements View.OnClickListener{
    private static final int MAX_WIDTH = 300;
    private static final int MAX_HEIGHT = 200;
    @Bind(R.id.doctorImageView)
    ImageView mImageLabel;
    @Bind(R.id.doctorNameTextView)
    TextView mNameLabel;
    @Bind(R.id.bioTextView)
    TextView mBioLabel;
    @Bind(R.id.genderTextView)
    TextView mGenderLabel;
    @Bind(R.id.websiteTextView)
    TextView mWebsiteLabel;
    @Bind(R.id.addressTextView)
    TextView mAddressLabel;
    @Bind(R.id.saveDoctorButton)
    TextView mSaveDoctorButton;
    private Doctor mDoctor;

    public static DoctorDetailFragment newInstance(Doctor doctor) {
        DoctorDetailFragment doctorDetailFragment = new DoctorDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("doctor", Parcels.wrap(doctor));
        doctorDetailFragment.setArguments(args);
        return doctorDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDoctor = Parcels.unwrap(getArguments().getParcelable("doctor"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext())
                .load(mDoctor.getImage_url())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);

        mNameLabel.setText(mDoctor.getName());
        mGenderLabel.setText(mDoctor.getGender());
        mAddressLabel.setText(mDoctor.getAddress());
        mBioLabel.setText(mDoctor.getBio());
        mWebsiteLabel.setText(mDoctor.getWebsite());

        mWebsiteLabel.setOnClickListener(this);
        mSaveDoctorButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mDoctor.getWebsite()));
            startActivity(webIntent);
        } if (v == mSaveDoctorButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference doctorRef = FirebaseDatabase.getInstance().getReference
                    (Constants.FIREBASE_CHILD_SAVED_DOCTORS).child(uid);
            doctorRef.push().setValue(mDoctor);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_LONG).show();
        }
    }
}

