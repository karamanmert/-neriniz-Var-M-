package com.example.forev.yorumla.Adapters;

import android.content.Context;
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

import com.example.forev.yorumla.Fragments.ComputerFragment;
import com.example.forev.yorumla.Models.ComputerModel;
import com.example.forev.yorumla.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ComputerHomeAdapter extends RecyclerView.Adapter<ComputerHomeAdapter.MyViewHolder> {

    Context context;
    Fragment fragment;
    List<ComputerModel> list;

    public ComputerHomeAdapter(Context context, List<ComputerModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ComputerHomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_computer_home,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ComputerHomeAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.computerHomeTextView.setText(list.get(i).getHardwarename().toString());

        Picasso.get().load(list.get(i).getResim()).resize(0,125).into(myViewHolder.computerHomeImageView);

       myViewHolder.computerHomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("markaName", list.get(i).getMarkaname().toString() );
                bundle.putString("hardwareName", list.get(i).getHardwarename().toString() );
                bundle.putString("id",list.get(i).getId().toString());
                ComputerFragment computerFragment = new ComputerFragment();
                computerFragment.setArguments(bundle);
                FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayout, computerFragment);
                ft.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView computerHomeTextView;
        private ImageView computerHomeImageView;
        private ConstraintLayout computerHomeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            computerHomeTextView = itemView.findViewById(R.id.computerHomeTextView);
            computerHomeImageView = itemView.findViewById(R.id.computerHomeImageView);
            computerHomeLayout = itemView.findViewById(R.id.computerHomeLayout);
        }
    }
}
