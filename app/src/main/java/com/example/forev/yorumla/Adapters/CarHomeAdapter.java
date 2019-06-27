package com.example.forev.yorumla.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.forev.yorumla.Fragments.CarFragment;
import com.example.forev.yorumla.Models.CarModel;
import com.example.forev.yorumla.R;
import com.example.forev.yorumla.Utils.GetSharedPreferences;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CarHomeAdapter extends RecyclerView.Adapter<CarHomeAdapter.MyViewHolder> {

    Context context;
    Fragment fragment;
    List<CarModel> list;
    private SharedPreferences getSharedPreferences;
    private GetSharedPreferences preferences;
    Activity activity;

    public CarHomeAdapter(Context context, List<CarModel> list, Activity activity) {
        this.context = context;
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CarHomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_car_home,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CarHomeAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.carHomeTextView.setText(list.get(i).getModel().toString());

        Picasso.get().load(list.get(i).getResim()).resize(0,125).into(myViewHolder.carHomeImageView);

       myViewHolder.carHomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("marka", list.get(i).getMarka().toString() );
                bundle.putString("model", list.get(i).getModel().toString() );
                bundle.putString("motor", list.get(i).getMotor().toString() );
                bundle.putString("id", list.get(i).getId().toString() );
                preferences = new GetSharedPreferences(activity);
                getSharedPreferences = preferences.getSession();
                bundle.putString("userid",getSharedPreferences.getString("id",null));
                CarFragment fragInfo = new CarFragment();
                fragInfo.setArguments(bundle);
                FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayout, fragInfo);
                ft.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView carHomeTextView;
        private ImageView carHomeImageView;
        private ConstraintLayout carHomeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            carHomeTextView = itemView.findViewById(R.id.carHomeTextView);
            carHomeImageView = itemView.findViewById(R.id.carHomeImageView);
            carHomeLayout = itemView.findViewById(R.id.carHomeLayout);
        }
    }
}
