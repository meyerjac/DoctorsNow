package com.example.jacksonmeyer.sidekick.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jacksonmeyer.sidekick.R;
import com.example.jacksonmeyer.sidekick.models.Doctor;
import com.example.jacksonmeyer.sidekick.ui.DoctorDetailActivity;
import com.squareup.picasso.Picasso;
import org.parceler.Parcels;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder>  {
    public static final String TAG = "TAG";
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


    public class DoctorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private static final int MAX_WIDTH = 200;
        private static final int MAX_HEIGHT = 200;
        @Bind(R.id.doctorImageView) ImageView mDoctorImageView;
        @Bind(R.id.doctorName) TextView mDoctorName;
        @Bind(R.id.bio) TextView mBio;
        @Bind(R.id.gender) TextView mGender;

        private Context mContext;


        public DoctorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }
        public void bindDoctor(Doctor doctor) {

            Picasso.with(mContext)
                    .load(doctor.getImage_url())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mDoctorImageView);


            mDoctorName.setText(doctor.getName());
            mBio.setText(doctor.getBio());
            mGender.setText(doctor.getGender());
        }

            @Override
            public void onClick(View v) {
                Log.d("click listener", "working!");
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, DoctorDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("doctors", Parcels.wrap(mDoctors));
                mContext.startActivity(intent);


            }
        }
    }


