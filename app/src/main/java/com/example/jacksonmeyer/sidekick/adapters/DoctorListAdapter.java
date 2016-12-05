package com.example.jacksonmeyer.sidekick.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jacksonmeyer.sidekick.R;
import com.example.jacksonmeyer.sidekick.models.Doctor;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder> {
    private ArrayList<Doctor> mDoctors = new ArrayList<>();
    private Context mContext;

    public DoctorListAdapter(Context context, ArrayList<Doctor> doctors) {
        mContext = context;
        mDoctors = doctors;
    }

    @Override
    public DoctorListAdapter.DoctorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list_item, parent, false);
        DoctorViewHolder viewHolder = new DoctorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DoctorListAdapter.DoctorViewHolder holder, int position) {
        holder.bindDoctor(mDoctors.get(position));
    }

    @Override
    public int getItemCount() {
        return mDoctors.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.doctorImageView) ImageView mDoctorImageView;
        @Bind(R.id.doctorName) TextView mDoctorName;
        @Bind(R.id.bio) TextView mBio;
        @Bind(R.id.address) TextView mAddress;
        private Context mContext;

        public DoctorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindDoctor(Doctor doctor) {
            mDoctorName.setText(doctor.getName());
            mBio.setText(doctor.getBio());
            mAddress.setText(doctor.getAddress());
        }
    }
}
