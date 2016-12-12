package com.example.jacksonmeyer.sidekick.ui;

        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import com.example.jacksonmeyer.sidekick.R;
        import com.example.jacksonmeyer.sidekick.models.Doctor;
        import com.squareup.picasso.Picasso;
        import org.parceler.Parcels;
        import butterknife.Bind;
        import butterknife.ButterKnife;

public class DoctorDetailFragment extends Fragment {
    @Bind(R.id.doctorImageView) ImageView mImageLabel;
    @Bind(R.id.doctorNameTextView) TextView mNameLabel;
    @Bind(R.id.bioTextView) TextView mBioLabel;
    @Bind(R.id.genderTextView) TextView mGenderLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveDoctorButton) TextView mSaveDoctorButton;

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

        Picasso.with(view.getContext()).load(mDoctor.getImage_url()).into(mImageLabel);

        mNameLabel.setText(mDoctor.getName());
        mGenderLabel.setText(mDoctor.getGender());
        mAddressLabel.setText(mDoctor.getAddress());

        return view;
    }
}
